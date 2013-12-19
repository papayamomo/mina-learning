package design_pattern.bridge;

public class TestMain {
	public static void main(String[] args) {

		Table tbl1 = new Table1(new Insert());
		tbl1.work();

		Table tbl2 = new Table2(new Update());
		tbl2.work();

		// Table tbl1 = new Table1(new Insert());
		// tbl1.work();
		// Table tbl2 = new Table2(new Update());
		// tbl2.work();

	}
}
