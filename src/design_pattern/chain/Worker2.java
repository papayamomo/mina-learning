package design_pattern.chain;

public class Worker2 implements IWorker {

	private IWorker next;

	public void handleIphone(Iphone iphone) {
		iphone.setState(iphone.getState() + "A pendant is hung up on me. ");
		if (next != null)
			next.handleIphone(iphone);
	}

	public void setNext(IWorker worker) {
		this.next = worker;
	}
}