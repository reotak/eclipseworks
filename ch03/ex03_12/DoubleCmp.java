package ex03_12;

class DoubleCmp implements ICmp {
	public int compare(Object o1, Object o2) {
		double d1 = (Double)o1;
		double d2 = (Double)o2;

		if (d1 > d2) {
			return 1;
		} else if (d1 == d2) {
			return 0;
		} else {
			return -1;
		}
	}
}
