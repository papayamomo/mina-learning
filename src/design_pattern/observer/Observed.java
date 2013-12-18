package design_pattern.observer;

public class Observed extends Subject {

	private String state;

	// to notify actually
	public void change() {
		this.notifyObservers();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
