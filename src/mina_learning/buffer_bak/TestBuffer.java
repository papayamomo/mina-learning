package mina_learning.buffer_bak;

public class TestBuffer {

	public static void main(String[] args) {
		IoBuffer buffer = IoBuffer.allocate(1024);
		System.out.println(buffer);
	}
}
