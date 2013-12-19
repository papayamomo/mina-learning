package design_pattern.wrapper;

public class HtmlFilter extends MessageBoardDecorator {
	public HtmlFilter(MessageBoardHandler handler) {
		super(handler);
	}

	public String filter(String content) {
		String temp = super.filter(content);
		temp += "^^fitler html^^";
		return temp;
	}
}
