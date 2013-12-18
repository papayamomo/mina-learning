package design_pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class Subject {

	// Observer is from JDK
	private List<Observer> observers = new ArrayList<Observer>();

	/**
	 * add an Observer
	 */
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	/**
	 * remove an Observer
	 */
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	/**
	 * notify all Observers
	 */
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(null, this); // ??
		}
	}
}
