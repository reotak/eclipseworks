package ex13_02;

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
}
