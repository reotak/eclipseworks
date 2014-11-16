
public class Fibonacci {

	public static void main(String[] args) {
		final String title = "フィボナッチ数列（50以下）";
		
		int lo = 1;
		int hi = 1;
		System.out.println(title); //名前付き文字列定数
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しい hi
			lo = hi - lo; /* 新しい lo は、（合計 - 古い lo)
							 すなわち、古い hi */

		}

	}

}
