package design_pattern.wrapper;

public class SensitiveFilter extends MessageBoardDecorator {
	public SensitiveFilter(MessageBoardHandler handler) {
		super(handler);
	}

	public String filter(String content) {
		String temp = super.filter(content);
		temp += "^^filter^^";
		return temp;
	}
}