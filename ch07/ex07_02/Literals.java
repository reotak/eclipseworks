package ex07_02;

class Literals {
	static char c = Character.MAX_VALUE;
	static byte b = Byte.MAX_VALUE;
	static short s = Short.MAX_VALUE;
	static int i = Integer.MAX_VALUE;
	static long lo = Long.MAX_VALUE;
	static float f = Float.MAX_VALUE;
	static double d = Double.MAX_VALUE;

	public static void main(String[] args) {
		System.out.println("==== Down Cast ====");
		printBaseAndTo(s, (byte)s);
		printBaseAndTo(i, (short)i);
		printBaseAndTo(lo, (int)lo);
		printBaseAndTo(f, (int)f);
		printBaseAndTo(f, (long)f);
		printBaseAndTo(d, (float)d);

		System.out.println("==== Up Cast ====");
		printBaseAndTo(b, (short)b);
		printBaseAndTo(s, (int)s);
		printBaseAndTo(i, (long)i);
		printBaseAndTo(lo, (float)lo);
		printBaseAndTo(f, (double)f);
	}

	public static void printBaseAndTo(Object base, Object to) {
		System.out.println(base.toString() + " -> " + to.toString());
	}

}
