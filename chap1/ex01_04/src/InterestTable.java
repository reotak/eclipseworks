
public class InterestTable {

	public static void main(String[] args) {

		System.out.println("100円を借りて返さなかった場合の借金表\n");

		System.out.println("      利率     1ヶ月     三ヶ月       半年       一年      二年");

		printRateLow(1.01, 24);

		printRateLow(1.03, 24);
		printRateLow(1.05, 24);
		printRateLow(1.08, 24);
		printRateLow(1.10, 24);
		printRateLow(1.20, 24);

	}

	static void printRateLow(double rate, int forMonth) {
		double rateMul = 100;
		System.out.printf(doubleTo10Chars(rate)); //Print rate

		for (int month = 1; month <= forMonth; month++) {
			// calc rate this month
			rateMul *= rate;

			if (month == 1 || month == 3 || month == 6 || month == 12 || month == 24) {
				System.out.print(doubleTo10Chars(rateMul));
			}
		}

		System.out.println();
	}

	static String doubleTo10Chars(double d) {
		String d2s = String.format("%6.3f", d);
		String result = "";

		for (int i = d2s.length(); i <= 10; i++)
			result += " ";

		result += d2s;

		return result;
	}

}
