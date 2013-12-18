package design_pattern.threadpool;

public class Worker implements Runnable {
	private int id;

	public Worker(int id) {
		this.id = id;
	}

	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread:" + Thread.currentThread().getName()
				+ " is started" + id);
	}
}
