package mina_learning.service_bak;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class AbstractIoAcceptor extends AbstractIoService implements
		IoAcceptor {

	private final List<SocketAddress> defaultLocalAddresses = new ArrayList<SocketAddress>();

	private final Set<SocketAddress> boundAddresses = new HashSet<SocketAddress>();

	protected final Object bindLock = new Object();

	/**
	 * @param param
	 *            sessionConfig
	 * @param executor
	 */
	protected AbstractIoAcceptor(Object param, Executor executor) {
		super(param, executor);
		defaultLocalAddresses.add(null);
	}

	@Override
	public SocketAddress getLocalAddress() {
		Set<SocketAddress> localAddresses = getLocalAddresses();
		if (localAddresses.isEmpty()) {
			return null;
		}
		return localAddresses.iterator().next();
	}

	@Override
	public final Set<SocketAddress> getLocalAddresses() {
		Set<SocketAddress> localAddresses = new HashSet<SocketAddress>();
		synchronized (boundAddresses) {
			localAddresses.addAll(boundAddresses);
		}
		return localAddresses;
	}

	@Override
	public void bind(SocketAddress localAddress) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void bind(Iterable<? extends SocketAddress> localAddresses)
			throws IOException {
		// TODO isDisposing()

		if (localAddresses == null) {
			throw new IllegalArgumentException("localAddresses");
		}

		List<SocketAddress> localAddressesCopy = new ArrayList<SocketAddress>();

		for (SocketAddress a : localAddresses) {
			// TODO check address type
			localAddressesCopy.add(a);
		}

		if (localAddressesCopy.isEmpty()) {
			throw new IllegalArgumentException("localAddresses is empty");
		}

		boolean active = false;

		synchronized (bindLock) {
			synchronized (boundAddresses) {
				if (boundAddresses.isEmpty()) {
					active = true;
				}
			}
		}
		/** implement in abstractIoService */
		if (getHandler() == null) {
			throw new IllegalArgumentException("handler is not set");
		}

		try {
			Set<SocketAddress> addresses = bindInternal(localAddressesCopy);

			synchronized (boundAddresses) {
				boundAddresses.addAll(addresses);
			}
		} catch (IOException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new RuntimeException("Filed ti bind");
		}

		if (active) {
			// do sth
		}
	}

	protected abstract Set<SocketAddress> bindInternal(
			List<? extends SocketAddress> localAddress) throws Exception;

	@Override
	public void unbind(SocketAddress localAddress) {
		// TODO Auto-generated method stub

	}
}