package ex16_09;

import static ex16_09.Util.*;

import java.util.List;

class ClassFormat {
	final String name;
	final String modifier;
	final String superName;
	final List<String> interfaces;
	final List<ClassFormat> innerClasses;
	final List<FieldFormat> fields;
	final List<MethodFormat> methods;
	final int tabDepth;

	public ClassFormat(String name, String modifier, String superName,  List<String> interfaces,
			List<ClassFormat> innerClasses, List<FieldFormat> fields, List<MethodFormat> methods, int tabDepth) {
		this.name = name;
		this.modifier = modifier;
		this.superName = superName;
		this.interfaces = interfaces;
		this.innerClasses = innerClasses;
		this.fields = fields;
		this.methods = methods;
		this.tabDepth = tabDepth;
	}

	public String toString() {
		String result = "";
		String tabs = "";
		for (int i = 0; i < tabDepth; i++) {
			tabs += "\t";
		}

		// public static class Name
		result += tabs + modifier + " ";
		result += name + " ";

		// extends
		if (!isEmpty(superName)) {
			result += "extends ";
			result += superName;
			result += " ";
		}

		// implements
		if (interfaces != null && interfaces.size() >= 1) {
			result += "implements ";
			result += insertString(", ", interfaces);
			result += " ";
		}

		result += " {\n";

		// innerClass
		if (innerClasses != null && innerClasses.size() != 0) {
			 result += insertString(";\n", innerClasses);
			 result += ";\n\n";
		}

		// field
		if (fields != null && fields.size() != 0) {
			result += tabs + "\t" + insertString(";\n" + tabs + "\t", fields);
			result += ";\n\n";
		}

		// method
		if (methods != null && methods.size() != 0) {
			result += tabs + "\t" + insertString(";\n" + tabs + "\t", methods);
			result += ";\n";
		}

		result += tabs + "}";

		return result;
	}
}
