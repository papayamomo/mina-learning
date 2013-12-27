package diy.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ToyNioClient {

	private static int BLOCK = 1024 * 1024 * 10;
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
	private static ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
	private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress(
			"127.0.0.1", 6666);

	public ToyNioClient() {
	}

	private void connect() throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(true);
		socketChannel.connect(SERVER_ADDRESS);
		socketChannel.finishConnect();

		InputStreamReader stdin = new InputStreamReader(System.in);
		BufferedReader bufin = new BufferedReader(stdin);

		sendBuffer.clear();
		sendBuffer.put(bufin.readLine().getBytes());
		sendBuffer.flip();
		socketChannel.write(sendBuffer);

		String receiveText;
		int count = 0;

		receiveBuffer.clear();
		count = socketChannel.read(receiveBuffer);

		if (count > 0) {
			receiveText = new String(receiveBuffer.array(), 0, count);
			System.out.println("Client Received Message:" + receiveText);
		} else {
			System.out.println("Client Received NULL.");
		}

		socketChannel.close();
	}

	public static void main(String[] args) throws IOException {
		ToyNioClient client = new ToyNioClient();
		client.connect();
	}
}
