package design_pattern.threadpool;

public class TestMain {
	public static void main(String[] args) {
		ThreadPool queue = new ThreadPool(10);

		queue.execute(new Worker(1));
		queue.execute(new Worker(2));
		queue.execute(new Worker(3));
	}
}
