package ex03_12;

import static org.junit.Assert.*;

import org.junit.Test;


public class SortHarnessTest {

	@Test
	public void testSortAsInteger() {
		Integer[] data1 = {4, 3, 0, 5, 1, 2};

		SortHarness.sort(data1, new IntegerCmp());

		boolean isSorted = true;
		for (int i = 1; i < data1.length; i++) {
			if (data1[i - 1] > data1[i]) {
				isSorted = false;
			}
		}

		assertTrue(isSorted);
	}

	@Test
	public void testSortAsDouble() {
		Double[] data1 = {4.7, 3.2, 0.8, 5.1, 1.9, 2.0};

		SortHarness.sort(data1, new DoubleCmp());

		boolean isSorted = true;
		for (int i = 1; i < data1.length; i++) {
			if (data1[i - 1] > data1[i]) {
				isSorted = false;
			}
		}

		assertTrue(isSorted);
	}

	
}
