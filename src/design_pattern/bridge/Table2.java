package design_pattern.bridge;

public class Table2 extends Table {

	public Table2(Operator impl) {
		super(impl);
	}

	public void work() {
		System.out.println(this.impl.work("Table2"));
	}
}
