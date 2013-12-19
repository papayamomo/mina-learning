package design_pattern.bridge;

public abstract class Table {

	protected Operator impl = null;

	public Table(Operator impl) {
		this.impl = impl;
	}

	/**
	 * Impl by sub class
	 */
	public abstract void work();

}
