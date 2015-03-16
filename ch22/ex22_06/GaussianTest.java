package ex22_06;

import java.util.Random;

public class GaussianTest {
	private static double[] result = new double[10000];
	private static final int CLASS_SIZE = 10;

	public static void main(String[] args) {
		Random r = new Random();

		for (int i = 0; i < result.length; i++) {
			result[i] = r.nextGaussian();
		}

		// get min and max value
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < result.length; i++) {
			max = Math.max(result[i], max);
			min = Math.min(result[i], min);
		}

		// CLASS_SIZE個のクラスに分配
		int[] classCount = new int[CLASS_SIZE];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < CLASS_SIZE; j++) {
				if (result[i] <= min + ((max - min) / CLASS_SIZE) * (j + 1)) {
					classCount[j]++;
					break;
				}
			}
		}

		printBarGraph(classCount, 50);
	}

	public static void printBarGraph(int[] data, int maxBarLength) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			max = Math.max(data[i], max);
		}

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < (data[i] * maxBarLength) / max; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
