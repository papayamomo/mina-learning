package design_pattern.chain;

public interface IWorker {

	/**
	 * handle method
	 */
	void handleIphone(Iphone iphone);

	/**
	 * set the next worker
	 */
	void setNext(IWorker worker);
}
