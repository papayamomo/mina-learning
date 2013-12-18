package design_pattern.chain;

public class Worker1 implements IWorker {

	private IWorker next;

	public void handleIphone(Iphone iphone) {
		iphone.setState(iphone.getState()
				+ "A shell will be installed into my body. ");
		if (next != null)
			next.handleIphone(iphone);
	}

	public void setNext(IWorker worker) {
		this.next = worker;

	}

}
