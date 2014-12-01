package ex13_01;

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
}
