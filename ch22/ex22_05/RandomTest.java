package ex22_05;

import java.util.Random;

public class RandomTest {
	private static int[] resultCount = new int[6];

	private static void resetResultCount() {
		for (int i = 0; i < resultCount.length; i++) {
			resultCount[i] = 0;
		}
	}

	private static void printResultCount() {
		for (int i = 0; i < resultCount.length; i++) {
			System.out.printf("  %d : %5d\n", i + 1, resultCount[i]);
		}
	}

	public static void main(String[] arg) {
		Random r = new Random();

		System.out.println("----- nextInt --------");
		resetResultCount();
		for (int i = 0; i < 100000; i++) {
			resultCount[r.nextInt(6)]++;
		}
		printResultCount();

		System.out.println("------ nextDouble --------");
		resetResultCount();
		for (int i = 0; i < 100000; i++) {
			double rand = r.nextDouble();

			for (int j = 0; j < 6; j++) {
				// nextDoubleの取りうる値を6分する
				if (rand <= (1.0d / 6.0d) * (j + 1)) {
					resultCount[j]++;
					break;
				}
			}
		}
		printResultCount();
	}
}
