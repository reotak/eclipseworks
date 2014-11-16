package ex10_01;

class EscapeChar {
	public static String unescape(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '\n')
				result += "\\n";
			else if (c == '\t')
				result += "\\t";
			else if (c == '\b')
				result += "\\b";
			else if (c == '\r')
				result += "\\r";
			else if (c == '\f')
				result += "\\f";
			else if (c == '\\')
				result += "\\\\";
			else if (c == '\'')
				result += "\\\'";
			else if (c == '\"')
				result += "\\\"";
			else
				result += c;
		}

		return result;
	}
}
