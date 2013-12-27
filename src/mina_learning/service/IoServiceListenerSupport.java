package mina_learning.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import mina_learning.service_bak.IoAcceptor;
import mina_learning.session.IoSession;

public class IoServiceListenerSupport {

	private final IoService service;

	private final List<IoServiceListener> listeners = new CopyOnWriteArrayList<IoServiceListener>();

	// the session set
	private final ConcurrentMap<Long, IoSession> managedSessions = new ConcurrentHashMap<Long, IoSession>();

	private final Map<Long, IoSession> readOnlyManagedSessions = Collections
			.unmodifiableMap(managedSessions);// this is read only session set

	// whether the service is activated
	private final AtomicBoolean activated = new AtomicBoolean();

	public void fireSessionCreated(IoSession session) {
		boolean firstSession = false;
		// if this is a client request
		if (session.getService() instanceof IoConnector) {
			synchronized (managedSessions) {
				firstSession = managedSessions.isEmpty();// empty?
			}
		}

		if (managedSessions.putIfAbsent(Long.valueOf(session.getId()), session) != null) {
			// If already registered, ignore.
			return;
		}

		if (firstSession) {// if this is the first session, fire a vitrual
							// service
			fireServiceActivated();
		}

		// call the fliter events
		session.getFilterChain().fireSessionCreated();
		session.getFilterChain().fireSessionOpened();
		int managedSessionCount = managedSessions.size();

		// the number of sessions
		if (managedSessionCount > largestManagedSessionCount) {
			largestManagedSessionCount = managedSessionCount;
		}
		cumulativeManagedSessionCount++;

		// listener
		for (IoServiceListener l : listeners) {
			try {
				l.sessionCreated(session);
			} catch (Throwable e) {
				ExceptionMonitor.getInstance().exceptionCaught(e);
			}
		}
	}

	private void disconnectSessions() {
		if (!(service instanceof IoAcceptor)) {// Make sure IoAcceptor
			return;
		}

		// deactivated time of IoAcceptor
		if (!((IoAcceptor) service).isCloseOnDeactivation()) {
			return;
		}

		Object lock = new Object();
		IoFutureListener<IoFuture> listener = new LockNotifyingListener(lock);
		for (IoSession s : managedSessions.values()) {
			s.close().addListener(listener);// add a listener for earch close
		}

		try {
			synchronized (lock) {
				while (!managedSessions.isEmpty()) {// wait
					lock.wait(500);
				}
			}
		} catch (InterruptedException ie) {
			// Ignored
		}

	}

	private static class LockNotifyingListener implements
			IoFutureListener<IoFuture> {

		private final Object lock;

		public LockNotifyingListener(Object lock) {
			this.lock = lock;
		}

		public void operationComplete(IoFuture future) {
			synchronized (lock) {
				lock.notifyAll();
			}
		}
	}
}
