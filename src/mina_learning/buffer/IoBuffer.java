package mina_learning.buffer;

public abstract class IoBuffer {

	private static IoBufferAllocator allocator = new SimpleBufferAllocator();
	private static boolean direct;
	
	protected IoBuffer() {
		//do nothing
	}
	
	public static IoBuffer allocate(int capacity) {
		return allocator.allocate(capacity, direct);
	}
	
	public static IoBuffer wrap(byte[] byteArray, int offset, int length) {
		// TODO
		return null;
	}
	
	public abstract IoBuffer get();
	public abstract IoBuffer put(byte b);
	public abstract boolean other();
}
