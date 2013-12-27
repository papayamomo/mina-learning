package diy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ToyNioServer {

	private int BLOCK = 1024 * 1024 * 10;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
	private Selector selector;

	public ToyNioServer(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server Start at£º" + port);
	}

	private void listen() throws IOException {
		while (true) {
			int select = selector.select();

			if (select == 0)
				continue;

			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				handleKey(selectionKey);
			}
		}
	}

	private void handleKey(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String receiveText;
		int count = 0;

		if (selectionKey.isAcceptable()) {
			server = (ServerSocketChannel) selectionKey.channel();
			client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			client = (SocketChannel) selectionKey.channel();
			receiveBuffer.clear();
			count = client.read(receiveBuffer);
			if (count > 0) {
				receiveText = new String(receiveBuffer.array(), 0, count);
				System.out.println("Server Received Message:" + receiveText);
			}

			receiveBuffer.flip();
			sendBuffer.clear();
			sendBuffer.put("Hello!".getBytes());
			sendBuffer.flip();
			client.write(sendBuffer);

			selectionKey.cancel();
		}
	}

	public static void main(String[] args) throws IOException {
		int port = 6666;
		ToyNioServer server = new ToyNioServer(port);
		server.listen();
	}

}
