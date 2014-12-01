package ex13_03;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static int countChar(String s, char c) {
		if (s == null) {
			throw new IllegalArgumentException("s is not null");
		}

		int count = 0;
		for (int index = 0; (index = s.indexOf(c, index)) >= 0; index++) {
			count++;
		}

		return count;
	}

	public static int countSpecialChar(String s) {
		if (s == null) {
			throw new IllegalArgumentException("s is not null");
		}

		List<Character> specialChars = new ArrayList<Character>();
		specialChars.add('\n');
		specialChars.add('\t');
		specialChars.add('\b');
		specialChars.add('\r');
		specialChars.add('\f');
		specialChars.add('\\');
		specialChars.add('\'');
		specialChars.add('\"');

		int count = 0;
		for (char sc : specialChars) {
			count += countChar(s, sc);
		}

		return count;
	}

	public static String delimitedString(String from, char start, char end) {
		int startPos = from.indexOf(start);
		int endPos = from.lastIndexOf(start);
		if (startPos == -1) {
			return null;
		} else if (endPos == -1) {
			return from.substring(startPos);
		} else if (startPos > endPos) {
			return null;
		} else {
			return from.substring(startPos, endPos + 1);
		}
	}

	public static String[] delimitedStrings(String from, char start, char end) {
		List<String> subs = new ArrayList<String>();

		List<Integer> startPoss = new ArrayList<Integer>();
		for (int index = from.indexOf(start, 0); index >= 0; index = from.indexOf(start, ++index)) {
			startPoss.add(index);
		}

		List<Integer> endPoss = new ArrayList<Integer>();
		for (int index = from.indexOf(end, 0); index >= 0; index = from.indexOf(end, ++index)) {
			endPoss.add(index);
		}

		for (int sp : startPoss) {
			for (int ep : endPoss) {
				if (sp > ep) {
					break;
				}
				subs.add(from.substring(sp, ep + 1));
			}
		}

		return (String[])subs.toArray(new String[0]);
	}
}
