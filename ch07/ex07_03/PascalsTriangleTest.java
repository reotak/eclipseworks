package ex07_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class PascalsTriangleTest {

	// int[][]型のaとbを比較し、完全に同じであればtrue, そうでなければfalseを返す
	private static boolean isArrayEqual(final int[][] a, final int[][] b) {
		if (a.length < 0 || b.length < 0) {
			throw new IllegalArgumentException("input less than zero value");
		}
		if (a.length != b.length) {
			return false;
		}
		
		for (int i = 0; i < a.length; i++) {
			if (a[i].length != b[i].length) {
				return false;
			}
			
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] != b[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	@Test
	public void calcConstTest() {
		assertTrue(isArrayEqual(PascalsTriangle.calc(1), new int[][]{ { 1 } }));
		assertTrue(isArrayEqual(PascalsTriangle.calc(2), new int[][]{ { 1 }, { 1, 1 } }));
		assertTrue(isArrayEqual(PascalsTriangle.calc(3), new int[][]{ { 1 }, { 1, 1 }, { 1, 2, 1 } }));
		assertTrue(isArrayEqual(PascalsTriangle.calc(4), new int[][]{ { 1 }, { 1, 1 }, { 1, 2, 1 }, { 1, 3, 3, 1 } }));
	}
	
	
	private static boolean firstAndLasIndexTest(int[][] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("input value is less than zero");
		}
		
		for (int i = 1; i < a.length; i++) {
			//各列の一番目や最後の値は常に1
			if (a[i][0] != 1 && a[i][a[i].length - 1] != 1) {
				return false;
			}
		}
		
		return true;
	}
	@Test
	public void calcFirstAndLastIndexTest() {
		assertTrue(firstAndLasIndexTest(PascalsTriangle.calc(1000)));
	}
	
	private static boolean secondIndexTest(final int[][] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("input value is less than zero");
		}
		
		for (int i = 1; i < a.length; i++) {
			//各列の二番目の引数は常に深さと等しい
			if (a[i][1] != i) {
				return false;
			}
		}
		
		return true;
	}
	
	@Test
	public void calc2ndIndexTest() {
		assertTrue(secondIndexTest(PascalsTriangle.calc(1000)));
	}

	// 全てのnにおける、a[n][]の配列が左右対称であるかを調べる
	private boolean isSymmetryArrays(final int[][] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("input value is less than zero");
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < (a[i].length / 2); j++) {
				if (a[i][j] != a[i][a[i].length - 1 - j]) {
					return false;
				}
			}
		}
			
		return true;
	}
			
	@Test
	public void calcIsSymmetryTest() {
		// パスカルの三角系ならば、各列は左右対称である
		assertTrue(isSymmetryArrays(PascalsTriangle.calc(1000)));
	}
}
