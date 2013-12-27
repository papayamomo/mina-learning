package mina_learning.service;

import mina_learning.session.IdleStatus;
import mina_learning.session.IoSession;

public interface IoHandler {

	void sessionCreated(IoSession session) throws Exception;// create Session

	// open session with another thread
	void sessionOpened(IoSession session) throws Exception;

	void sessionClosed(IoSession session) throws Exception;// close session

	// session idle
	void sessionIdle(IoSession session, IdleStatus status) throws Exception;

	// catch the exception and disconnect the connection
	void exceptionCaught(IoSession session, Throwable cause) throws Exception;

	// receive the message
	void messageReceived(IoSession session, Object message) throws Exception;

	// send the message
	void messageSent(IoSession session, Object message) throws Exception;
}
