package mina_learning.buffer;

public class TestBuffer {

	public static void main(String[] args) {
		IoBuffer buffer = IoBuffer.allocate(4);

		for (int i = 0; i < 10; ++i) {
			buffer.put("a".getBytes());
		}

		System.out.println(buffer);
	}
}
