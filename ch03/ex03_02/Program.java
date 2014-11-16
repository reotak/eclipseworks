package ex03_02;

class X {
	protected int xMask = 0x00ff;
	protected int fullMask;

	public X() {
		System.out.printf("[X constractor start] fullMask : %04x%n", fullMask);
		fullMask = xMask;
		System.out.printf("[X constractor end  ] fullMask : %04x%n", fullMask);
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}
}

class Y extends X {
	protected int yMask = 0xff00;

	public Y() {
		System.out.printf("[Y constractor start] fullMask : %04x%n", fullMask);
		fullMask |= yMask;
		System.out.printf("[Y constractor end  ] fullMask : %04x%n", fullMask);
	}
}
public class Program {

	public static void main(String[] args) {
		System.out.println("--- create X ---");
		X x = new X();

		System.out.println("--- create Y ---");
		Y y = new Y();
	}


}
