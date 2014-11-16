package ex10_05;

class BetweenChars {
	public static void printBetweenChars(char c1, char c2) {
		if (c1 > c2) {
			char tmp = c1;
			c1 = c2;
			c2 = tmp;
		}
		
		for (char c = c1; c <= c2; c++) 
			System.out.print(c);
		System.out.println();
	}
	
	public static void main(String[] args) {
		printBetweenChars('a', 'z');
		printBetweenChars('z', 'a');
	}
}
