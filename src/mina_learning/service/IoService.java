package mina_learning.service;

import java.util.Map;
import java.util.Set;

import mina_learning.session.IoSession;

public interface IoService {
	// network provider (NIO, ARP, RXTX)
	TransportMetadata getTransportMetadata();

	// Listener
	void addListener(IoServiceListener listener);

	void removeListener(IoServiceListener listener);

	/**
	 * Returns <tt>true</tt> if and if only {@link #dispose()} method has been
	 * called. Please note that this method will return <tt>true</tt> even after
	 * all the related resources are released.
	 */
	boolean isDisposing();

	/**
	 * Returns <tt>true</tt> if and if only all resources of this processor have
	 * been disposed.
	 */
	boolean isDisposed();

	/**
	 * Releases any resources allocated by this service. Please note that this
	 * method might block as long as there are any sessions managed by this
	 * service.
	 */
	void dispose();

	/**
	 * Releases any resources allocated by this service. Please note that this
	 * method might block as long as there are any sessions managed by this
	 * service.
	 * 
	 * Warning : calling this method from a IoFutureListener with
	 * <code>awaitTermination</code> = true will probably lead to a deadlock.
	 * 
	 * @param awaitTermination
	 *            When true this method will block until the underlying
	 *            ExecutorService is terminated
	 */
	void dispose(boolean awaitTermination);

	// IoHandler
	IoHandler getHandler();

	void setHandler(IoHandler handler);

	// Managed the session set, and broadcast messages.
	Map<Long, IoSession> getManagedSessions();

	/**
	 * Returns the number of all sessions which are currently managed by this
	 * service.
	 */
	int getManagedSessionCount();

	// default session config : IoSessionConfig¡£
	IoSessionConfig getSessionConfig();

	// IoFilterChain for each session. Build by IoFilterChainBuilder
	IoFilterChainBuilder getFilterChainBuilder();

	void setFilterChainBuilder(IoFilterChainBuilder builder);

	DefaultIoFilterChainBuilder getFilterChain();

	/**
	 * @return whether of not the service is active.
	 */
	boolean isActive();

	/**
	 * Returns the time when this service was activated. It returns the last
	 * time when this service was activated if the service is not active now.
	 * 
	 * @return The time by using {@link System#currentTimeMillis()}
	 */
	long getActivationTime();

	// broadcast and received the callback -- future
	Set<WriteFuture> broadcast(Object message);

	// create the data structure for a session
	IoSessionDataStructureFactory getSessionDataStructureFactory();

	void setSessionDataStructureFactory(
			IoSessionDataStructureFactory sessionDataStructureFactory);

	/**
	 * @return The number of bytes scheduled to be written
	 */
	int getScheduledWriteBytes();

	/**
	 * @return The number of messages scheduled to be written
	 */
	int getScheduledWriteMessages();

	// statistics
	IoServiceStatistics getStatistics();
}
