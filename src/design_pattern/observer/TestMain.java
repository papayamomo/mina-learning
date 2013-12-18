package design_pattern.observer;

public class TestMain {

	public static void main(String[] args) {
		Observed observed = new Observed();
		observed.setState("I am Observed");
		observed.addObserver(new Observer1());
		observed.addObserver(new Observer2());
		observed.addObserver(new Observer3());

		observed.change();
	}
}
