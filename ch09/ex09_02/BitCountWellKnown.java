package ex09_02;

class BitCountWellKnown {
	public static int bitCount(int x) {
		int n = 0;
		while (x != 0) {
			++n;
			x &= x - 1;
		}
		return n;
	}
}
