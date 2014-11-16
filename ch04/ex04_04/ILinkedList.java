package ex04_04;

interface ILinkedList extends IArray {
	boolean add(int index, Object value);
	boolean add(ILinkedList prev, Object value);
	Object find(Object key);
}
