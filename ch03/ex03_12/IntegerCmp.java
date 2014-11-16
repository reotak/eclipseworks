package ex03_12;

class IntegerCmp implements ICmp {
	public int compare(Object o1, Object o2) {
		int i1 = (Integer)o1;
		int i2 = (Integer)o2;

		if (i1 > i2) {
			return 1;
		} else if (i1 == i2) {
			return 0;
		} else {
			return -1;
		}
	}
}
