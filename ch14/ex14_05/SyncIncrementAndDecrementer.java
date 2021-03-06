package ex14_05;

class SyncIncrementAndDecrementer {
	static int num = 0;

	public static void incPrint() {
		synchronized (SyncIncrementAndDecrementer.class) {
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
	}

	public static void decPrint() {
		synchronized (SyncIncrementAndDecrementer.class) {
			int buf = num;

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			buf--;
			System.out.printf("%s dec: %8d->%8d%n", Thread.currentThread().getName(), num, buf);
			num = buf;
		}
	}

	public static void main(String []args) {
		incPrint();
		decPrint();
		incPrint();
		decPrint();
	}
}