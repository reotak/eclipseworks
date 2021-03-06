package ex14_06;


class Program {
	public static void main(String []args) {
		TimeDisplay disp = new TimeDisplay();

		new Thread(new MessagePrinterRunner(disp, "15sec!!", 15), "15sec message").start();
		new Thread(new MessagePrinterRunner(disp, "07sec!!",  7), "07sec message").start();
		new Thread(new DisplayRunner(disp), "Time Display").start();
	}
}

class MessagePrinterRunner implements Runnable {
	private final TimeDisplay disp;
	private final String message;
	private final int notifyCount;

	public MessagePrinterRunner(TimeDisplay disp, String message, int notifyCount) {
		this.disp = disp;
		this.message = message;
		this.notifyCount = notifyCount;
	}
	public void run() {
		disp.runMessagePrinter(message, notifyCount);
	}
};

class DisplayRunner implements Runnable {
	final TimeDisplay disp;
	public DisplayRunner(TimeDisplay disp) {
		this.disp = disp;
	}

	public void run() {
		disp.runDisplay();
	}
}