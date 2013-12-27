package mina_learning.service;

import java.util.Map;
import java.util.Set;

import mina_learning.session.IoSession;

public class AbstractIoService implements IoService {

	@Override
	public TransportMetadata getTransportMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(IoServiceListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(IoServiceListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDisposing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose(boolean awaitTermination) {
		// TODO Auto-generated method stub

	}

	@Override
	public IoHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHandler(IoHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Long, IoSession> getManagedSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getManagedSessionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IoSessionConfig getSessionConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IoFilterChainBuilder getFilterChainBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFilterChainBuilder(IoFilterChainBuilder builder) {
		// TODO Auto-generated method stub

	}

	@Override
	public DefaultIoFilterChainBuilder getFilterChain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getActivationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<WriteFuture> broadcast(Object message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IoSessionDataStructureFactory getSessionDataStructureFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSessionDataStructureFactory(
			IoSessionDataStructureFactory sessionDataStructureFactory) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getScheduledWriteBytes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScheduledWriteMessages() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IoServiceStatistics getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

}
