
public class Fibonacci {

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しい hi
			lo = hi - lo; /* 新しい lo は、（合計 - 古い lo)
							 すなわち、古い hi */

		}

	}

}
