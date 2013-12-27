package mina_learning.service;

import mina_learning.session.IdleStatus;
import mina_learning.session.IoSession;

public interface IoServiceListener {

	// active a new service
	void serviceActivated(IoService service) throws Exception;

	// service idle
	void serviceIdle(IoService service, IdleStatus idleStatus) throws Exception;

	// deactivate a service
	void serviceDeactivated(IoService service) throws Exception;

	// create a session
	void sessionCreated(IoSession session) throws Exception;

	// destroy a session
	void sessionDestroyed(IoSession session) throws Exception;

}