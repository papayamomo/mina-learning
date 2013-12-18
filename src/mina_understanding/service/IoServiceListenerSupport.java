package mina_understanding.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import mina_learning.service_bak.IoService;

public class IoServiceListenerSupport {

	private final List<IoServiceListener> listeners = new CopyOnWriteArrayList<IoServiceListener>();

	public void add(IoServiceListener listener) {
		if (listener != null) {
			listeners.add(listener);
		}
	}

	public void remove(IoServiceListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	private IoService service;

	private final AtomicBoolean activated = new AtomicBoolean();

	/**
	 * Notify method ......................................................
	 * Calls {@link IoServiceListener#serviceActivated(IoService)} for all
	 * registered listeners.
	 */
	public void fireServiceActivated() {
		if (!activated.compareAndSet(false, true)) {
			// The instance is already active
			return;
		}

		// Activate all the listeners now
		for (IoServiceListener listener : listeners) {// execute the method of
														// each listener
			try {
				listener.serviceActivated(service);
			} catch (Throwable e) {
				// ExceptionMonitor.getInstance().exceptionCaught(e);
			}
		}
	}

}
