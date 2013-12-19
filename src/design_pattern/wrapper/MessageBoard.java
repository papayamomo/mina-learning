package design_pattern.wrapper;

public class MessageBoard implements MessageBoardHandler {
	public String filter(String msg) {
		return "Message:" + msg;
	}
}
