package mina_learning.buffer_bak;

import java.nio.ByteBuffer;

public class SimpleBufferAllocator implements IoBufferAllocator {

	@Override
	public IoBuffer allocate(int capacity, boolean direct) {
		return wrap(allocateNioBuffer(capacity, direct));
	}

	@Override
	public IoBuffer wrap(ByteBuffer nioBuffer) {
		return new SimpleBuffer(nioBuffer);
	}

	@Override
	public ByteBuffer allocateNioBuffer(int capacity, boolean direct) {
		ByteBuffer nioBuffer;
		if (direct) {
			nioBuffer = ByteBuffer.allocateDirect(capacity);
		} else {
			nioBuffer = ByteBuffer.allocate(capacity);
		}
		return nioBuffer;
	}

	// @Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	private class SimpleBuffer extends AbstractIoBuffer {
		ByteBuffer buffer;

		protected SimpleBuffer(ByteBuffer buffer) {
			super(buffer);
			this.buffer = buffer;
		}

		@Override
		public boolean other() {
			// TODO Auto-generated method stub
			return false;
		}

		/** For printing */
		@Override
		public String toString() {
			System.out.println(buffer);
			return super.toString();
		}
	}

}
