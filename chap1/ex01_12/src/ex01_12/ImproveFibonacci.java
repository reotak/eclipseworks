package ex01_12;


public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		String[] fibonacci = new String[MAX_INDEX];

		fibonacci[0] = "1: " + lo;
		for (int i = 2; i <= MAX_INDEX; i++) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			fibonacci[i - 1] = i + ": " + hi + mark;
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int i = 0; i < fibonacci.length; i++) {
			System.out.println(fibonacci[i]);
		}
	}
}
