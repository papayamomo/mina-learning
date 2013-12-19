package design_pattern.facade;

public class ComputerFactory {

	public Computer saleComputer() {
		Computer computer = new Computer();
		System.out.println("saled computer");
		return computer;
	}

}

// public class Store {
// public Coat saleCoat() {
// CoatFactory coatFactory = new CoatFactory();
// return coatFactory.saleCoat();
// }
//
// public Computer saleComputer() {
// ComputerFactory computerFactory = new ComputerFactory();
// return computerFactory.saleComputer();
// }
// }
