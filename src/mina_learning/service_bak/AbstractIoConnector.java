package mina_learning.service_bak;

import java.net.SocketAddress;

public abstract class AbstractIoConnector {

	public final ConnectFuture connect(SocketAddress remoteAddress,
			SocketAddress localAddress,
			IoSessionInitializer<? extends ConnectFuture> sessionInitializer) {

		if (getHandler() == null) {
			if (getSessionConfig().isUseReadOperation()) {
				setHandler(new IoHandler() {
					public void exceptionCaught(IoSession session,
							Throwable cause) throws Exception {
						// Empty handler
					}

					public void messageReceived(IoSession session,
							Object message) throws Exception {
						// Empty handler
					}

					public void messageSent(IoSession session, Object message)
							throws Exception {
						// Empty handler
					}

					public void sessionClosed(IoSession session)
							throws Exception {
						// Empty handler
					}

					public void sessionCreated(IoSession session)
							throws Exception {
						// Empty handler
					}

					public void sessionIdle(IoSession session, IdleStatus status)
							throws Exception {
						// Empty handler
					}

					public void sessionOpened(IoSession session)
							throws Exception {
						// Empty handler
					}
				});
			} else {
				throw new IllegalStateException("handler is not set.");
			}
		}

		return connect0(remoteAddress, localAddress, sessionInitializer);
	}

	protected abstract ConnectFuture connect0(SocketAddress remoteAddress,
			SocketAddress localAddress,
			IoSessionInitializer<? extends ConnectFuture> sessionInitializer);

}
