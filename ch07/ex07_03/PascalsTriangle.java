package ex07_03;

class PascalsTriangle {
	
	public static int[][] calc(final int depth) {
		if (depth <= 0) {
			throw new IllegalArgumentException("depth <= 0");
		}
		
		int[][] triangle =  new int[depth][];
		
		// initialize
		triangle[0] = new int[]{ 1 };
		
		for (int dep = 0; dep < triangle.length - 1; dep++) {
			// create next array
			triangle[dep + 1] = new int[dep + 2];
			
			// calc next array
			for (int i = 0; i < triangle[dep].length; i++) {
				triangle[dep + 1][i] += triangle[dep][i];
				triangle[dep + 1][i + 1] += triangle[dep][i];
			}
		}
		
		return triangle;
	}
	
	public static void main(String[] args) {
		System.out.println(" 12 depth PascalsTriangle");
		int[][] res = PascalsTriangle.calc(12);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(" 18 depth PascalsTriangle");
		res = PascalsTriangle.calc(18);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
