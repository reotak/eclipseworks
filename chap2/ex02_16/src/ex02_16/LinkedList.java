package ex02_16;

import java.util.ArrayList;
import java.util.List;

class InfinityLoopException extends Exception { }

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
}
