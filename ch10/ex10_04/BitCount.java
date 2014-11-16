package ex10_04;

class BitCount {
	public static int bitCount(final int num) {
		int count = 0;
		int mask = 0x01;
		while (mask != 0x00) {
			if ((num & mask) != 0x00) {
				count++;
			}
			mask <<= 1;
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(bitCount(0x00000000));
		System.out.println(bitCount(0x11111111));
		System.out.println(bitCount(0xffffffff));
		System.out.println(bitCount(0x0000000f));
		System.out.println(bitCount(0xf0000000));
	}

}
