package design_pattern.adapter;

public class TestMain {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.request();
	}
}
