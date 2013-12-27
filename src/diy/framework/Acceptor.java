package diy.framework;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Acceptor implements Runnable {

	private int BLOCK = 1024 * 1024 * 10;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
	private Selector selector;

	public Acceptor(Selector selector) throws IOException {
		this.selector = selector;
	}

	@Override
	public void run() {
		while (true) {
			int select = 0;
			try {
				select = selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (select == 0)
				continue;

			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				try {
					handleKey(selectionKey);
				} catch (IOException e) {
					e.printStackTrace();
				}
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

}
