package ex11_01;


public class LinkedList<E> implements Cloneable{

	private E value;
	private LinkedList<E> next;

	public LinkedList(E value) {
		this.value = value;
		this.next = null;
	}

	public LinkedList(E value, LinkedList<E> next) {
		this.value = value;
		this.next = next;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public LinkedList<E> getNext() {
		return next;
	}

	public void setNext(LinkedList<E> next) {
		this.next = next;
	}

	// リストの最後に追加する
	// 何番目の要素として追加したか返す
	// maxCount以上の要素が検出された場合 -1 を返す
	public int add(E value, int maxCount) {
		int count = 0;

		LinkedList<E> next;
		for (next = this; next.getNext() != null; next = next.getNext()) {
			if (++count > maxCount) {
				return -1;
			}
		}

		next.setNext(new LinkedList<E>(value));

		return ++count;
	}

	// maxCount以上の場合 -1 を返す
	public int countList(int maxCount) {
		int count = 0;

		for (LinkedList<E> next = this; next != null; next = next.getNext()) {
			if (count > maxCount) {
				return -1;
			}
			count++;
		}

		return count;
	}

	public String toString() {
		String result = "";
		result += this.getValue().toString();
		if (this.getNext() != null) {
			result += " -> " + this.getNext().toString();
		}

		return result;
	}

	public LinkedList<E> clone() {
		try {
			LinkedList<E> clone = (LinkedList<E>)super.clone();

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
