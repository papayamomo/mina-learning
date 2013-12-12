package mina_learning.transport_bak;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Executor;

import mina_learning.polling_bak.AbstractPollingIoAcceptor;

public final class NioSocketAcceptor extends AbstractPollingIoAcceptor
		implements SocketAcceptor {

	private volatile Selector selector;

	protected NioSocketAcceptor(Object param, Executor executor) {
		super(param, executor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getManagedSessionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * extends from AbstractIoAcceptor
	 * 
	 * The type NioSocketAcceptor must implement the inherited abstract method
	 * SocketAcceptor.getLocalAddress() to override
	 * AbstractIoAcceptor.getLocalAddress()
	 */
	@Override
	public InetSocketAddress getLocalAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultLocalAddress(InetSocketAddress localAddress) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isReuseAddress() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void init() throws Exception {
		selector = Selector.open();
	}

	@Override
	protected void destory() throws Exception {
		if (selector != null) {
			selector.close();
		}
	}

	@Override
	protected int select() throws Exception {
		return selector.select();
	}

	@Override
	protected void dispose0() throws Exception {
		// TODO Auto-generated method stub

	}

	protected ServerSocketChannel open(SocketAddress localAddress)
			throws Exception {
		ServerSocketChannel channel = ServerSocketChannel.open();

		boolean success = false;

		try {
			channel.configureBlocking(false);

			ServerSocket socket = channel.socket();

			socket.setReuseAddress(isReuseAddress());

			socket.bind(localAddress);

			channel.register(selector, SelectionKey.OP_ACCEPT);

			success = true;
		} finally {
			if (!success) {
				// close(channel);
			}
		}
		return channel;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

}
