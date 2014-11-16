
public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		Number[] fibonacci = new Number[MAX_INDEX];

		// init
		fibonacci[0] = new Number(1);
		fibonacci[1] = new Number(1);

		// calc fibonacci under 50
		for (int i = 2; i < MAX_INDEX; i++) {
			fibonacci[i] = new Number(fibonacci[i - 1].getNum() + fibonacci[i - 2].getNum());
		}

		// print fibonacci
		for (int i = 0; i < MAX_INDEX; i++) {
			System.out.println(fibonacci[i].getNum() + (fibonacci[i].isEven() ? "*" : ""));
		}
	}
}

class Number {
	private int num;

	public Number(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public boolean isEven() {
		return (num % 2) == 0;
	}

}