package ex14_04;


class SyncIncrementerRunner implements Runnable {
	final int count;

	public SyncIncrementerRunner(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("want: count >= 0");
		}
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++) {
			SyncIncrementer.incPrint();
		}
	}

	public static void main(String []args) {
		int count = 100;

		new Thread(new SyncIncrementerRunner(count), "runner1").start();
		new Thread(new SyncIncrementerRunner(count), "runner2").start();
		new Thread(new SyncIncrementerRunner(count), "runner3").start();
		new Thread(new SyncIncrementerRunner(count), "runner4").start();
	}
}
