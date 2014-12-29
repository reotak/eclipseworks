package ex14_04;

class SyncIncrementer {
	static int num = 0;

	public static synchronized void incPrint() {
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
		incPrint();
		incPrint();
		incPrint();
	}
}