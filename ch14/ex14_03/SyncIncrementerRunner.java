package ex14_03;


class SyncIncrementerRunner implements Runnable {
	SyncIncrementer adder;
	final int count;

	public SyncIncrementerRunner(SyncIncrementer adder, int count) {
		if (adder == null) {
			throw new IllegalArgumentException("want: adder is not null");
		}
		if (count < 0) {
			throw new IllegalArgumentException("want: count >= 0");
		}

		this.adder = adder;
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++) {
			adder.incPrint();
		}
	}

	public static void main(String []args) {
		SyncIncrementer adder = new SyncIncrementer(0);
		int count = 100;

		new Thread(new SyncIncrementerRunner(adder, count), "runner1").start();
		new Thread(new SyncIncrementerRunner(adder, count), "runner2").start();
		new Thread(new SyncIncrementerRunner(adder, count), "runner3").start();
		new Thread(new SyncIncrementerRunner(adder, count), "runner4").start();
	}
}
