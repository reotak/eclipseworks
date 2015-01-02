package ex16_09;

import static ex16_09.Util.*;

import java.util.List;

class MethodFormat {
	private final String name;
	private final String returnType;
	private final String modifier;
	private final List<String> paramTypes;
	private final List<String> exceptions;

	public MethodFormat(String name, String returnType, String modifier, List<String> paramTypes, List<String> exceptions) {
		this.name = name;
		this.returnType = returnType;
		this.modifier = modifier;
		this.paramTypes = paramTypes;
		this.exceptions = exceptions;
	}

	public String toString() {
		String result = "";

		if (!isEmpty(modifier)) {
			result += modifier + " ";
		}
		result += returnType + " ";
		result += name;

		result += "(";
		if (paramTypes != null && paramTypes.size() != 0) {
			result += insertString(", ", paramTypes);
		}
		result += ")";

		if (exceptions != null && exceptions.size() != 0) {
			result += " throws ";
			result += insertString(", ", exceptions);
		}

		return result;
	}
}
