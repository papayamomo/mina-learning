package design_pattern.adapter;

public class Adapter implements Target {
	Adaptee adaptee;

	public Adapter() {
		adaptee = new Adaptee();
	}

	public void request() {
		adaptee.specificRequest();
	}
}
