package design_pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Observer1 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Observed observed = (Observed) arg;
		System.out.println("O1 received notify: " + observed.getState()
				+ "; O1 happy!");
	}
}