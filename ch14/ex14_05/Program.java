package ex14_05;

public class Program {
	public static void main(String []args) {
		int count = 100;

		new Thread(new SyncIncrementRunner(count), "inc1").start();
		new Thread(new SyncDecrementRunner(count), "dec1").start();
		new Thread(new SyncIncrementRunner(count), "inc2").start();
		new Thread(new SyncDecrementRunner(count), "dec2").start();
	}
}