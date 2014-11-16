package ex01_09;

public class Fibonacci {

	public static void main(String[] args) {
		int[] fibonacci = new int[50];
		int size = 0;

		// init
		fibonacci[size++] = 1;
		fibonacci[size++] = 1;

		// calc fibonacci under 50
		while (fibonacci[size - 1] + fibonacci[size - 2] < 50) {
			fibonacci[size] = fibonacci[size - 1] + fibonacci[size - 2];
			size++;
		}

		// print fibonacci
		System.out.println("フィボナッチ数列（50以下）");
		for (int i = 0; i < size ; i++) {
			System.out.println(fibonacci[i]);
		}
	}

}

