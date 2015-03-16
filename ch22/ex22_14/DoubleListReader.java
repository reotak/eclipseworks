package ex22_14;

import java.util.StringTokenizer;

public class DoubleListReader {
	public static double sum(String doubleList) {
		StringTokenizer st = new StringTokenizer(doubleList, " ");

		double total = 0.0;
		while (st.hasMoreTokens()) {
			try {
				total += new Double(st.nextToken());
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("空白で区切られた浮動小数点ではありません" + e.toString());
			}
		}

		return total;
	}


}
