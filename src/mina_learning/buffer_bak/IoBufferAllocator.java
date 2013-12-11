package mina_learning.buffer_bak;

import java.nio.ByteBuffer;

public interface IoBufferAllocator {

	IoBuffer allocate(int capacity, boolean direct);

	IoBuffer wrap(ByteBuffer nioBuffer);

	ByteBuffer allocateNioBuffer(int capacity, boolean direct);

	void dispose();
}
