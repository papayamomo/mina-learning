package design_pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Observer2 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Observed observed = (Observed) arg;
		System.out.println("O2 received notify: " + observed.getState()
				+ "; O2 happy!");
	}
}