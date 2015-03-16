package ex19_01;

import java.util.ArrayList;
import java.util.List;

/**
 * Object 型の要素を持つ線形連結リストを示すクラスです。
 * @author Yamato
 * @version 1.0
 */
public class LinkedList {
	private Object data;
	private LinkedList next;

	/**
	 * このリストの要素の値を指定して、インスタンスを生成します。
	 * このコンストラクタでインスタンスを生成した場合、このリストは次に続かないものとして生成します。
	 * LinkedList(data) と LinkedList(data, null) は同じです。
	 * @param data このリストの要素の値
	 */
	public LinkedList(Object data) {
		this(data, null);
	}

	/**
	 * このリストの要素の値と、次に続くリストを指定して、インスタンスを生成します。
	 * このリストが次に続かないことを示す場合は next に null を与えてください。
	 * @param data このリストの要素の値
	 * @param next このリストの次に続くリスト
	 */
	public LinkedList(Object data, LinkedList next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * 要素の値を取得します
	 * @return 要素の値
	 */
	public Object getData() {
		return data;
	}

	/**
	 * このリストの要素の値を設定します
	 * @param data 要素の値
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * このリストの次に続くリストを返します。
	 * 次に続くリストがない場合、nullを返します。
	 * @return このリストの次に続くリスト
	 */
	public LinkedList getNext() {
		return next;
	}

	/**
	 * このリストの次に続くリストを設定します。
	 * 次に続かないことを示す場合は next に null を与えてください。
	 * @param next このリストの次に続くリスト
	 */
	public void setNext(LinkedList next) {
		this.next = next;
	}


	/**
	 * このリストに続くリストの数を返します。このリスト自身は数に含みません。
	 * maxCount 以上に続くリストであった場合、-1 を返します。
	 * @param maxCount 最大いくつまで調べるかを設定します
	 * @return このリストに続く要素の数（このリスト自身は含まない）。maxCount以上に続くリストであった場合 -1 を返す
	 * @throws IllegalStateException このリストが無限に続くリストであった場合にスローします
	 */
	public int countList(int maxCount) throws IllegalStateException {
		int count = 0;
		LinkedList next = this;
		List<LinkedList> searched = new ArrayList<LinkedList>();

		while (next != null) {
			if (count > maxCount) {
				return -1;
			}

			if (searched.contains(next)) {
				throw new IllegalStateException("無限に続くリストです");
			} else {
				searched.add(next);
			}

			next = next.getNext();
			count++;
		}

		return count;
	}

	/**
	 * このリストの要素と、それに続くリストの要素の値から成る文字列を返します。
	 * 無限に続くリストの場合、終了しない不具合があります。
	 * 実行する場合は、countList(int maxCount) メソッドで、事前に無限に続くリストではないことを確認してから実行して下さい。
	 * @return このリストの要素と、それに続くリストの要素の値から成る文字列
	 */
	public String toString() {
		String result = "";
		result += this.getData().toString();
		if (this.getNext() != null) {
			result += " -> " + this.getNext().toString();
		}

		return result;
	}
}
