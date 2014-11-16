package ex04_03;

import java.util.ArrayList;
import java.util.List;

class InfinityLoopException extends Exception {
	private static final long serialVersionUID = 1L;
}

// 一部関数のみ巡回リストを考慮に入れているが原則として巡回リストは考慮しない
public class LinkedList implements Cloneable, ILinkedList {
	private Object data;
	private LinkedList next;

	public LinkedList(Object data) {
		this(data, null);
	}

	public LinkedList(Object data, LinkedList next) {
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

	// この関数のみ巡回リストを考慮に入れている
	public int countList(int maxCount) throws InfinityLoopException {
		int count = 0;
		LinkedList next = this;
		List<LinkedList> searched = new ArrayList<LinkedList>();

		while (next != null) {
			if (count > maxCount) {
				return -1;
			}

			if (searched.contains(next)) {
				throw new InfinityLoopException();
			} else {
				searched.add(next);
			}

			next = next.getNext();
			count++;
		}

		return count;
	}

	public String toString() {
		String result = "";
		result += this.getData().toString();
		if (this.getNext() != null) {
			result += " -> " + this.getNext().toString();
		}

		return result;
	}

	public LinkedList clone() {
		try {
			LinkedList clone = (LinkedList)super.clone();

			// set new next
			if (clone.next != null) {
				clone.next = clone.next.clone();
			}
			return clone;

		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
}
