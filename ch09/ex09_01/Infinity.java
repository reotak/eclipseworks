package ex09_01;

class Infinity {

	public static void main(String[] args) {
		System.out.println("(+inf) op (+inf)");
		printResultFourOperation(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
		System.out.println("(+inf) op (-inf)");
		printResultFourOperation(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY);
		System.out.println("(-inf) op (+inf)");
		printResultFourOperation(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
		System.out.println("(-inf) op (-inf)");
		printResultFourOperation(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
	}

	static void printResultFourOperation(float f1, float f2) {
		System.out.println(" + : " + (f1 + f2));
		System.out.println(" - : " + (f1 - f2));
		System.out.println(" * : " + (f1 * f2));
		System.out.println(" / : " + (f1 / f2));
	}

}
