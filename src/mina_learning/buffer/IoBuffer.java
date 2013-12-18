package mina_learning.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class IoBuffer {

	private static IoBufferAllocator allocator = new IoBufferAllocator();
	private static boolean direct;

	public static IoBuffer allocate(int capacity) {
		return allocator.allocate(capacity, direct);
	}

	public static IoBuffer wrap(byte[] byteArray, int offset, int length) {
		// TODO
		return null;
	}

	ByteBuffer buffer;

	protected IoBuffer(ByteBuffer buffer) {
		// super(buffer);
		this.buffer = buffer;
	}

	public Byte get() {
		return buffer.get();
	}

	public IoBuffer put(byte b) {
		capacity(buffer.position() + 1);
		buffer.put(b);
		return this;
	}

	public IoBuffer put(byte[] bytes) {
		capacity(buffer.position() + bytes.length);
		buffer.put(bytes);
		return this;
	}

	public boolean other() {
		return false;
	}

	/** For printing */
	public String toString() {
		System.out.println(buffer);
		return super.toString();
	}

	public final IoBuffer capacity(int newCapacity) {
		// Allocate a new buffer and transfer all settings to it.
		if (newCapacity > buffer.capacity()) {
			// Expand:
			// // Save the state.
			int pos = buffer.position();
			// int limit = buffer.limit();
			ByteOrder bo = buffer.order();

			// // Reallocate.
			ByteBuffer oldBuf = buffer;
			ByteBuffer newBuf = allocator
					.allocateNioBuffer(newCapacity, direct);
			oldBuf.clear();
			newBuf.put(oldBuf);
			buffer = newBuf;

			// // Restore the state.
			// buffer.limit(buffer.limit());
			buffer.position(pos);
			buffer.order(bo);
		}

		return this;
	}

	public Object remaining() {
		// TODO Auto-generated method stub
		return null;
	}
}