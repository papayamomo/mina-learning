package design_pattern.proxy;

//Proxy Subject
public class ProxySubject extends Subject {
	private RealSubject realSubject; // the real subject as the attribute

	public ProxySubject() {
	}

	public void request() { // the method packaged the real request
		preRequest();
		if (realSubject == null) {
			realSubject = new RealSubject();
		}
		realSubject.request(); // the execution of the real request
		postRequest();
	}

	private void preRequest() {
		// something you want to do before requesting
		System.out.println("PRE REQUEST");
	}

	private void postRequest() {
		// something you want to do after requesting
		System.out.println("POST REQUEST");
	}
}