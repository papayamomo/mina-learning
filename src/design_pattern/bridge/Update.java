package design_pattern.bridge;

public class Update implements Operator {

	public String work(String arg) {
		String rs = "update " + arg;
		return rs;
	}

}
