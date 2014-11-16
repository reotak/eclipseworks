package ex02_02;

public class LinkedList {
	private Object data;
	private LinkedList next;

	LinkedList(Object data) {
		this(data, null);
	}

	LinkedList(Object data, LinkedList next) {
		this.setData(data);
		this.setNext(next);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}
}
