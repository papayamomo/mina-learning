package diy.framework;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NioServer {

	private Selector selector;
	private Executor executor;

	public NioServer(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		if (executor == null) {
			this.executor = Executors.newCachedThreadPool();
		}

		System.out.println("Server Start at£º" + port);
	}

	public void listener() {
		try {
			Acceptor acceptor = new Acceptor(selector);
			executor.execute(acceptor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		int port = 6666;
		NioServer server = new NioServer(port);
		server.listener();
	}

}
