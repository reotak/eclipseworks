package ex10_02;

class EscapeChar {
	public static String unescape(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\n':
				result += "\\n";
				break;
			case '\t':
				result += "\\t";
				break;
			case '\b':
				result += "\\b";
				break;
			case '\r':
				result += "\\r";
				break;
			case '\f':
				result += "\\f";
				break;
			case '\\':
				result += "\\\\";
				break;
			case '\'':
				result += "\\\'";
				break;
			case '\"':
				result += "\\\"";
				break;
			default:
				result += c;
			}
		}

		return result;
	}
}
