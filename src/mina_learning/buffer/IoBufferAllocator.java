package mina_learning.buffer;

import java.nio.ByteBuffer;

public class IoBufferAllocator {

	public IoBuffer allocate(int capacity, boolean direct) {
		return wrap(allocateNioBuffer(capacity, direct));
	}

	public IoBuffer wrap(ByteBuffer nioBuffer) {
		return new IoBuffer(nioBuffer);
	}

	public ByteBuffer allocateNioBuffer(int capacity, boolean direct) {
		ByteBuffer nioBuffer;
		if (direct) {
			nioBuffer = ByteBuffer.allocateDirect(capacity);
		} else {
			nioBuffer = ByteBuffer.allocate(capacity);
		}
		return nioBuffer;
	}

	public void dispose() {
		// Do nothing
	}

}
