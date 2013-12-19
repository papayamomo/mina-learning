package design_pattern.bridge;

public class Insert implements Operator {

	public String work(String arg) {
		String rs = "insert " + arg;
		return rs;
	}

}
