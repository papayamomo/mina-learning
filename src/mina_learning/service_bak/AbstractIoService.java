package mina_learning.service_bak;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AbstractIoService implements IoService {

	private final String threadName;
	private static final AtomicInteger id = new AtomicInteger();

	private final Executor executor;
	private final boolean createdExecutor;

	// protected AbstractIoService(IoSessionConfig sessionConfig, Executor
	// executor) {
	protected AbstractIoService(Executor executor) {
		// Omission something

		if (executor == null) {
			this.executor = Executors.newCachedThreadPool();
			createdExecutor = true;
		} else {
			this.executor = executor;
			createdExecutor = false;
		}
		threadName = getClass().getSimpleName() + '-' + id.incrementAndGet();
	}
}
