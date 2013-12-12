package mina_learning.transport_bak;

import java.net.InetSocketAddress;

import mina_learning.service_bak.IoAcceptor;

public interface SocketAcceptor extends IoAcceptor {

	InetSocketAddress getLocalAddress();

	void setDefaultLocalAddress(InetSocketAddress localAddress);

	public boolean isReuseAddress();

	// ...
	// SocketSessionConfig getSessionConfig();
}