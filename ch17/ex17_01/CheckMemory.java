package ex17_01;

import java.util.ArrayList;
import java.util.List;

class CheckMemory {

	public static void main(String[] args) {
		System.out.println("program started : "
				+ Runtime.getRuntime().freeMemory());

		List<GabageObject> objs = new ArrayList<GabageObject>();

		final int ONE_MILLION = 1000000;
		for (int i = 0; i < ONE_MILLION * 10; i++) {
			objs.add(new GabageObject(i));
		}

		System.out.println("object created  : "
				+ Runtime.getRuntime().freeMemory());

		objs = null;

		for (int i = 0; i < 5; i++) {
			Runtime.getRuntime().runFinalization();
			Runtime.getRuntime().gc();

			System.out.println("required gc(" + i + ")  : "
					+ Runtime.getRuntime().freeMemory());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("sleeped(" + i + ")      : "
					+ Runtime.getRuntime().freeMemory());
		}
	}

	static class GabageObject {
		private int data;

		public GabageObject(int data) {
			this.data = data;
		}

		public String toString() {
			return data + "";
		}
	}
}
