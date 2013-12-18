package mina_understanding.session;

import java.nio.ByteBuffer;

import mina_learning.buffer.IoBuffer;
import mina_understanding.handler.IoHandler;

public class IoSession extends IoBuffer {

	IoHandler ioHandler = new IoHandler();

	public IoHandler getHandler() {
		return ioHandler;
	}

	protected IoSession(ByteBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}

	public void increaseReadMessages(long currentTimeMillis) {
		// TODO Auto-generated method stub

	}

	public void increaseReadBytes(Object remaining, long currentTimeMillis) {
		// TODO Auto-generated method stub

	}

}
