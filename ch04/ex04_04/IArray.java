package ex04_04;

interface IArray extends ICollection {
	Object get(int index);
	void set(int index, Object value);
	int size();
}
