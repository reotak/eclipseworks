package ex14_05;

public class SyncDecrementRunner implements Runnable{
	final int count;

	public SyncDecrementRunner(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("want: count >= 0");
		}
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++) {
			SyncIncrementAndDecrementer.decPrint();
		}
	}
}
