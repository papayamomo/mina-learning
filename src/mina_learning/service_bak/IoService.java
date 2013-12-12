package mina_learning.service_bak;

public interface IoService {
	/** add listener */
	void addListener(IoServiceListener listener);

	/** destroy */
	void dispose(boolean awaitTermination);

	IoHandler getHandler();

	void setHandler(IoHandler handler);

	/** manage session */
	int getManagedSessionCount();

	boolean isActive();
}
