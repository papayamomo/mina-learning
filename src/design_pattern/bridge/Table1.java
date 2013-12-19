package design_pattern.bridge;

public class Table1 extends Table {

	public Table1(Operator impl) {
		super(impl);
	}

	public void work() {
		System.out.println(this.impl.work("Table1"));
	}
}
