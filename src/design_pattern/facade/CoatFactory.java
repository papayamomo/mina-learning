package design_pattern.facade;

public class CoatFactory {

	public Coat saleCoat() {
		Coat coat = new Coat();
		System.out.println("saled coat");
		return coat;
	}
}
