package mina_learning.service_bak;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractIoService implements IoService {

	private final String threadName;
	private static final AtomicInteger id = new AtomicInteger();

	private final Executor executor;
	private final boolean createdExecutor;

	private IoHandler handler;

	// for closing safely
	protected final Object disposalLock = new Object();
	private volatile boolean disposing;
	private volatile boolean disposed;

	protected AbstractIoService(Object param, Executor executor) {
		// TODO listener & session config

		if (executor == null) {
			this.executor = Executors.newCachedThreadPool();
			createdExecutor = true;
		} else {
			this.executor = executor;
			createdExecutor = false;
		}
		threadName = getClass().getSimpleName() + '-' + id.incrementAndGet();
	}

	public void addListener(IoServiceListener listener) {
		// TODO add listener
	}

	protected final void executeWorker(Runnable worker, String suffix) {
		String actualThreadName = threadName;
		if (suffix != null) {
			actualThreadName = actualThreadName + "-" + suffix;
		}
		executor.execute(worker);
	}

	public IoHandler getHandler() {
		return this.handler;
	}

	public void setHandler(IoHandler handler) {
		this.handler = handler;
	}

	@Override
	public void dispose(boolean awaitTermination) {
		if (disposed) {
			return;
		}
		synchronized (disposalLock) {
			if (!disposing) {
				disposing = true;
				try {
					/** real close method TODO */
					dispose0();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (createdExecutor) {
			ExecutorService e = (ExecutorService) executor;
			e.shutdown();
			if (awaitTermination) {
				try {
					e.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
				} catch (InterruptedException e1) {
					// interrupt ?
					Thread.currentThread().interrupt();
				}
			}
		}
		disposed = true;
	}

	protected abstract void dispose0() throws Exception;

}
