package ex21_07;

import java.util.ArrayList;

public class Stack<E> {
	final private int capacity;
	final private ArrayList<E> datas;

	public Stack(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity is negative");
		}

		this.capacity = capacity;
		this.datas = new ArrayList<E>(capacity);
	}

	public boolean push(E e) {
		if (datas.size() == capacity) {
			return false;
		}
		datas.add(e);
		return true;
	}

	public E pop() {
		if (datas.size() <= 0) {
			return null;
		}
		return datas.remove(datas.size() - 1);
	}

	public E peek() {
		if (datas.size() <= 0) {
			return null;
		}
		return datas.get(datas.size() - 1);
	}
}
