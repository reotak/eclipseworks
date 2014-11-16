package ex03_12;

public class Sample {

	public static void main(String[] args) {
		Integer[] data = {4, 3, 0, 5, 1, 2};

		data = (Integer[])SortHarness.sort(data, new IntegerCmp());

		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
}

