package ex13_06;

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

	public static ArrayList<Object> typeValueFromatReader(String str) {
		ArrayList<Object> list = new ArrayList<Object>();

		for (String line :str.split("\n")) {
			if (line == "") { // 最後の改行のみは認める
				break;
			}

			String[] typeValuePair = line.split(" ");
			if (typeValuePair.length != 2) {
				throw new IllegalArgumentException(line + " is not \"Type Value\" format.");
			}

			Object value = strToValue(typeValuePair[0], typeValuePair[1]);
			list.add(value);
		}
		return list;
	}

	private static Object strToValue(String type, String value) {
		if (type.equals("Boolean")) {
			return Boolean.parseBoolean(value);
		} else if (type.equals("Byte")) {
			return Byte.parseByte(value);
		} else if (type.equals("Character")) {
			return value.charAt(0);
		} else if (type.equals("Short")) {
			return Short.parseShort(value);
		} else if (type.equals("Integer")) {
			return Integer.parseInt(value);
		} else if (type.equals("Long")) {
			return Long.parseLong(value);
		} else if (type.equals("Float")) {
			return Float.parseFloat(value);
		} else if (type.equals("Double")) {
			return Double.parseDouble(value);
		} else {
			throw new IllegalArgumentException(type + " is not type of Wrapper Class");
		}
	}

	public static void main(String[] args) {
		String str = "Integer 5\nCharacter c\nFloat 5.27293\n";
		str += "Short 2222\nByte 111\nLong 123123123123123123\n";
		str += "Double 1.23545231245\n";

		String res = StringUtil.separateNumber(str);

		System.out.println(res);
	}

	public static String separateNumber(String s) {
		return separateNumber(s, 3);
	}

	public static String separateNumber(String s, int interval) {
		StringBuilder result = new StringBuilder();

		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < sb.length(); i++) {
			if (!isNumber(sb.charAt(i))) {
				result.append(sb.charAt(i));
				continue;
			}

			String num = getNumberStr(sb , i);
			if (isContainDot(num)) {
				result.append(num);
				i += num.length() - 1;
				continue;
			}

			int headCharCount = num.length() % interval;

			for (int j = 0; j < headCharCount; j++) {
				result.append(sb.charAt(i));
				i++;
			}
			if (headCharCount == 0) {

			} else if (headCharCount < num.length()) {
				result.append(",");
			} else {
				i--;
				continue;
			}

			int count = 0;
			for (int j = 0; j < num.length() - headCharCount; j++) {
				if (count == interval) {
					result.append(",");
					result.append(sb.charAt(i));
					count = 1;
					i++;
				} else {
					result.append(sb.charAt(i));
					count++;
					i++;
				}
			}
			i--;
		}

		return result.toString();
	}

	private static boolean isNumber(char c) {
		if ('0' <= c && c <= '9') {
			return true;
		}

		return false;
	}

	private static String getNumberStr(StringBuilder sb, int start) {
		String result = "";

		int length = sb.length();
		while (start <= length && (isNumber(sb.charAt(start)) || sb.charAt(start) == '.')) {
			result += sb.charAt(start);
			start++;
		}

		return result;
	}

	private static boolean isContainDot(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '.') {
				return true;
			}
		}
		return false;
	}
}
