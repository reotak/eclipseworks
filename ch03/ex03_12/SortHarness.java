package ex03_12;

class SortHarness {

	public static Object[] sort(Object[] data, ICmp cmp) {
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (cmp.compare(data[i], data[j]) > 0)
					swap(data, i, j);
			}
		}

		return data;
	}

	private static void swap(Object[] data, int i, int j) {
		Object tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
}
