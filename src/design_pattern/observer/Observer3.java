package design_pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Observer3 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Observed observed = (Observed) arg;
		System.out.println("O3 received notify: " + observed.getState()
				+ "; O3 happy!");
	}
}