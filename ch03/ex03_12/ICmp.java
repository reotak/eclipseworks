package ex03_12;

interface ICmp {
	// if o1 is larger than o2, returns value larger than 0
	// if o1 is equals o2, returns 0
	// if o1 is smaller than o2, returns value smaller than 0
	int compare(Object o1, Object o2);
}
