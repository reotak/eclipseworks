package ex14_02;

class PrintServer implements Runnable {
	private Thread myThread;
	private final PrintQueue requests = new PrintQueue();

	public PrintServer() {
		myThread = new Thread(this);
		myThread.start();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	public void run() {
		if (Thread.currentThread() != myThread) {
			throw new RuntimeException("このメソッドは外部からの呼び出し禁止です");
		}
		for (;;)
			realPrint(requests.remove());
	}

	private void realPrint(PrintJob job) {
		if (job == null) {
			return;
		}

		// 印刷の実際の処理
		job.print();
	}

	public static void main(String []args) {
		PrintServer server = new PrintServer();

		PrintJob job = new PrintJob("Hello");
		server.print(job);

		job = new PrintJob(",");
		server.print(job);

		job = new PrintJob("World");
		server.print(job);

		job = new PrintJob(".");
		server.print(job);
	}
}
