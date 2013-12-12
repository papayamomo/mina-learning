package mina_learning.service_bak;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Set;

public interface IoAcceptor extends IoService {

	SocketAddress getLocalAddress();

	Set<SocketAddress> getLocalAddresses();

	void bind(SocketAddress localAddress) throws IOException;

	void bind(Iterable<? extends SocketAddress> localAddresses)
			throws IOException;

	void unbind(SocketAddress localAddress);

	// IoSession newSession(SocketAddress remoteAddress,SocketAddress
	// localAddress);
}