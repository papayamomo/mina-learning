package design_pattern.wrapper;

public class TestMain {
	// public static void main(String[] args) {
	// MessageBoardHandler mb = new MessageBoard();
	// String content = mb.filter("Decorator");
	// System.out.println(content);
	// }
	public static void main(String[] args) {
		MessageBoardHandler mb = new MessageBoard();
		String content = mb.filter("Decorator");
		System.out.println(content);

		mb = new HtmlFilter(new SensitiveFilter(new MessageBoard()));
		content = mb.filter("Decorator");
		System.out.println(content);
	}
}