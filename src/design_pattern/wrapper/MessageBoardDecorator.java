package design_pattern.wrapper;

public class MessageBoardDecorator implements MessageBoardHandler {
	private MessageBoardHandler handler;

	public MessageBoardDecorator(MessageBoardHandler handler) {
		super();
		this.handler = handler;
	}

	public String filter(String msg) {
		return handler.filter(msg);
	}
}
