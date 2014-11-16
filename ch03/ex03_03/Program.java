package ex03_03;

class X {
	protected int xMask = 0x00ff;
	protected int childMask;
	protected int fullMask;

	public X() {
		fullMask = childMask();
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	protected int childMask() {
		return xMask;
	}
}

class Y extends X {
	protected int childMask() {
		return 0xff00;
	}
}
public class Program {

	public static void main(String[] args) {
		X x = new X();
		System.out.printf("X in x : %04x%n", x.mask(0xffff));

		Y y = new Y();
		System.out.printf("Y in y :%04x%n", y.mask(0xffff));

		X x2 = new Y();
		System.out.printf("X in y : %04x%n", x2.mask(0xffff));
	}


}
