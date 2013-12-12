package mina_learning.polling_bak;

import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

import mina_learning.service_bak.AbstractIoAcceptor;

public abstract class AbstractPollingIoAcceptor extends AbstractIoAcceptor {

	private final Semaphore lock = new Semaphore(1);

	private volatile boolean selectable;

	private AtomicReference<Acceptor> acceptorRef = new AtomicReference<Acceptor>();

	/**
	 * define the num of sockets that can wait to be accepted.
	 */
	protected int backlog = 50;

	/**
	 * @param param
	 * @param executor
	 */
	protected AbstractPollingIoAcceptor(Object param, Executor executor) {
		super(param, executor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * init the polling system. will be called at construction time
	 * 
	 * @throws Exception
	 */
	protected abstract void init() throws Exception;

	protected abstract void destory() throws Exception;

	protected abstract int select() throws Exception;

	/** Modify? */
	protected abstract ServerSocketChannel open(SocketAddress localAddress)
			throws Exception;

	@Override
	protected Set<SocketAddress> bindInternal(
			List<? extends SocketAddress> localAddress) throws Exception {
		// ...
		try {
			lock.acquire();
			Thread.sleep(10);
		} finally {
			lock.release();
		}
		// ...
		return null;
	}

	/**
	 * this class is called by startupAcceptor() method it's a thread accepting
	 * incoming connections from client
	 * 
	 * @author ChenHui
	 * 
	 */
	private class Acceptor implements Runnable {
		@Override
		public void run() {
			assert (acceptorRef.get() == this);

			int nHandles = 0;

			lock.release();

			while (selectable) {
				try {
					int selected = select();

					// nHandles+=registerHandles();

					if (nHandles == 0) {
						acceptorRef.set(null);
						// ...
					}
				} catch (Exception e) {

				}
			}
		}
	}
}