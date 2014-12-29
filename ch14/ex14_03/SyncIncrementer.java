package ex14_03;

class SyncIncrementer {
	int num;

	public SyncIncrementer(int num) {
		this.num = num;
	}

	public synchronized void incPrint() {
		int buf = num;

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		buf++;
		System.out.printf("%s inc: %8d->%8d%n", Thread.currentThread().getName(), num, buf);
		num = buf;
	}

	public static void main(String []args) {
		SyncIncrementer adder = new SyncIncrementer(10);
		adder.incPrint();
		adder.incPrint();
		adder.incPrint();
	}
}