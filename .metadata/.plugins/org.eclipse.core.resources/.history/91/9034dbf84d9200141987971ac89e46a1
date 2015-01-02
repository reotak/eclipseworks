package ex16_09;
import static ex16_09.Util.*;

class FieldFormat {
	private final String name;
	private final String type;
	private final String modifier;

	public FieldFormat(String name, String type, String modifier) {
		this.name = name;
		this.type = type;
		this.modifier = modifier;
	}

	public String toString() {
		String result = "";

		if (!isEmpty(modifier)) {
			result += modifier + " ";
		}
		result += type + " ";
		result += name;

		return result;
	}
}
