package design_pattern.proxy;

//Real Subject 
public class RealSubject extends Subject {
	public RealSubject() {
	}

	public void request() {
		System.out.println("From real subject.");
	}
}