package design_pattern.wrapper;

public class MainTest {
	public static void main(String[] args) {
		MessageBoardHandler mb = new MessageBoard();
		String content = mb.filter("Decorator");
		System.out.println(content);
	}
}
