package ex22_02;

import java.util.HashSet;
import java.util.Iterator;

public class WhichChars {
	private HashSet<Character> used = new HashSet<Character>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			used.add(str.charAt(i));
		}
	}

	public String toString() {
		String desc = "[";
		Iterator<Character> iter = used.iterator();

		while (iter.hasNext()) {
			desc += iter.next();
		}
		return desc + "]";
	}

	public static void main(String[] args) {
		String target = "\uD800あいうえおHello, World.";
		WhichChars wc = new WhichChars(target);

		System.out.println("target : " + target);
		System.out.println("result : " + wc.toString());
	}
}