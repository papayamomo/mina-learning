package mina_understanding.filterchain;

import mina_understanding.session.IoSession;

public class IoFilter {

	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		nextFilter.messageReceived(session, message);
	}

	public class NextFilter {

		public void messageReceived(IoSession session, Object message) {
			// TODO Auto-generated method stub
		}

	}
}
