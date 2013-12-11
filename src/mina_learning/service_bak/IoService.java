package mina_learning.service_bak;

import java.util.Map;
import java.util.Set;

public interface IoService {

	TransportMetadata getTransportMetadata();

	void addListener(IoServiceListener listener);

	void removeListener(IoServiceListener listener);

	boolean isDisposing();

	boolean isDisposed();

	void dispose();

	void dispose(boolean awaitTermination);

	IoHandler getHandler();

	void setHandler(IoHandler handler);

	Map<Long, IoSession> getManagedSessions();

	int getManagedSessionCount();

	IoSessionConfig getSessionConfig();

	IoFilterChainBuilder getFilterChainBuilder();

	void setFilterChainBuilder(IoFilterChainBuilder builder);

	DefaultIoFilterChainBuilder getFilterChain();

	boolean isActive();

	long getActivationTime();

	Set<WriteFuture> broadcast(Object message);

	IoSessionDataStructureFactory getSessionDataStructureFactory();

	void setSessionDataStructureFactory(
			IoSessionDataStructureFactory sessionDataStructureFactory);

	int getScheduledWriteBytes();

	int getScheduledWriteMessages();

	IoServiceStatistics getStatistics();
}
