package mina_understanding.filterchain;

import mina_learning.buffer.IoBuffer;
import mina_understanding.filterchain.IoFilter.NextFilter;
import mina_understanding.session.IoSession;

public class IoFilterChain {

	/** The associated session */
	private final IoSession session;
	private Entry head;

	public IoFilterChain(IoSession session) {
		this.session = session;
		this.head = new Entry();
	}

	public void fireMessageReceived(Object message) {
		if (message instanceof IoBuffer) {
			session.increaseReadBytes(((IoBuffer) message).remaining(),
					System.currentTimeMillis());
		}

		Entry head = this.head;
		callNextMessageReceived(head, session, message);
	}

	public void messageReceived(Entry entry, IoSession session, Object message) {
		Entry nextEntry = entry.getNextEntry();
		callNextMessageReceived(nextEntry, session, message);
	}

	private void callNextMessageReceived(Entry entry, IoSession session,
			Object message) {
		try {
			IoFilter filter = entry.getFilter();
			NextFilter nextFilter = entry.getNextFilter();
			// Send the message to the next filter.
			filter.messageReceived(nextFilter, session, message);
		} catch (Throwable e) {
			// fireExceptionCaught(e);
		}
	}

	// last fitler
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		IoSession s = (IoSession) session;
		if (!(message instanceof IoBuffer)) {
			s.increaseReadMessages(System.currentTimeMillis());
		}

		try {
			// The message will arrived the handler after the last filter
			// finished
			session.getHandler().messageReceived(s, message);
		} finally {
			// if (s.getConfig().isUseReadOperation()) {
			// s.offerReadFuture(message);
			// }
		}
	}

	public class Entry {

		private Entry preEntry;
		private Entry nextEntry;
		private String name;
		private IoFilter filter;
		private NextFilter nextFilter;

		public Entry getPreEntry() {
			return preEntry;
		}

		public void setPreEntry(Entry preEntry) {
			this.preEntry = preEntry;
		}

		public Entry getNextEntry() {
			return nextEntry;
		}

		public void setNextEntry(Entry nextEntry) {
			this.nextEntry = nextEntry;
		}

		public IoFilter getFilter() {
			return filter;
		}

		public void setFilter(IoFilter Filter) {
			this.filter = Filter;
		}

		public String getName() {
			return name;
		}

		public NextFilter getNextFilter() {
			return nextFilter;
		}

		// public void fire() {
		// IoSession session = getSession();
		// NextFilter nextFilter = getNextFilter();
		// IoEventType type = getType();
		//
		// if (DEBUG) {
		// LOGGER.debug("Firing a {} event for session {}", type,
		// session.getId());
		// }
		//
		// switch (type) {
		// case MESSAGE_RECEIVED:
		// // launch the messageReceived
		// Object parameter = getParameter();
		// nextFilter.messageReceived(session, parameter);
		// break;
		//
		// }
		// }
	}

}
