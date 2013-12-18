package design_pattern.chain;

public class Worker3 implements IWorker {

	private IWorker next;

	public void handleIphone(Iphone iphone) {
		iphone.setState(iphone.getState() + "Now I am a good gift! ");
		if (next != null)
			next.handleIphone(iphone);
	}

	public void setNext(IWorker worker) {
		this.next = worker;
	}
}