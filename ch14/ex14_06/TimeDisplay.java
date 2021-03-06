package ex14_06;


public class TimeDisplay {
	public synchronized void runMessagePrinter(final String message, final int notifyCount) {
		if (message == null) {
			throw new IllegalArgumentException("want: message is not null");
		}
		if (notifyCount < 0) {
			throw new IllegalArgumentException("want: notifyCount is not negative");
		}

		int notifyCounter = 0;
		for (;;) {
			while (notifyCounter < notifyCount) {
				try {
					wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				notifyCounter++;
			}

			System.out.println(message);
			notifyCounter = 0;
		}
	}

	public void runDisplay() {
		int time = 1;
		for (;;) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			printDisplay(time++);
		}
	}

	public  synchronized void printDisplay(final int time) {
		System.out.println(" Time Display : " + time);
		notifyAll(); // 表示を行ったことを通知
	}
}
