package mina_learning.service_bak;

import java.util.concurrent.Executor;

public class AbstractIoAcceptor extends AbstractIoService implements IoAcceptor {

	protected AbstractIoAcceptor(IoSessionConfig sessionConfig,
			Executor executor) {
		super(sessionConfig, executor);
		defaultLocalAddresses.add(null);
	}

}
