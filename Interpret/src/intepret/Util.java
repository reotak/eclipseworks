package intepret;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class Util {
	public static boolean isEmpty(String str) {
		return str != null && str.length() == 0;
	}

	public static String insertString(String insert, String... strs) {
		if (strs == null) {
			return "";
		}

		List<String> list = new ArrayList<String>();

		for (String s : strs) {
			list.add(s);
		}

		return insertString(insert, list);
	}

	public static String insertString(String insert, List<?> list) {
		if (list == null) {
			return "";
		}

		String result = "";

		boolean isFirst = true;
		for (Object o : list) {
			if (isFirst) {
				isFirst = false;
			} else {
				result += insert;
			}

			if (o != null) {
				result += o.toString();
			}
		}

		return result;
	}

	public static String modifierToString(int modifier) {
		return modifierToString(modifier, false);
	}

	public static String modifierToString(int modifier, boolean isClass) {
		String result = "";
		result += isEmpty(getAccesser(modifier).toString()) ? "" : getAccesser(
				modifier).toString()
				+ " ";
		result += Modifier.isStatic(modifier) ? "static " : "";
		result += Modifier.isNative(modifier) ? "native " : "";
		result += Modifier.isStrict(modifier) ? "strict " : "";
		result += Modifier.isVolatile(modifier) ? "volatile " : "";
		result += Modifier.isSynchronized(modifier) ? "synchronized " : "";
		result += Modifier.isTransient(modifier) ? "trasient " : "";
		result += Modifier.isFinal(modifier) ? "final " : "";
		result += isClass ? (Modifier.isInterface(modifier) ? "interface "
				: "class ") : Modifier.isAbstract(modifier) ? "abstract " : "";

		if (isEmpty(result)) {
			return "";
		} else {
			// 最後の空白文字を削る
			return result.substring(0, result.length() - 1);
		}
	}

	public static String typeToString(Type t) {
		String result = "";
		if (t instanceof Class<?>) {
			result = ((Class<?>) t).getSimpleName();
		} else if (t instanceof GenericArrayType) {
			result = typeToString(((GenericArrayType) t)
					.getGenericComponentType()) + "[]";
		} else if (t instanceof ParameterizedType) {
			ParameterizedType param = ((ParameterizedType) t);
			result += typeToString(param.getRawType());
			result += "<";
			boolean isFirst = true;
			for (Type p : param.getActualTypeArguments()) {
				if (isFirst) {
					isFirst = false;
				} else {
					result += ", ";
				}
				result += typeToString(p);
			}
			result += ">";
		} else if (t instanceof TypeVariable<?>) {
			result = ((TypeVariable<?>) t).getName();
		} else if (t instanceof WildcardType) {
			result = ((WildcardType) t).toString();
		} else {
			assert false : "Typeが解決できませんでした";
		}

		return result;
	}

	private static Accesser getAccesser(int modifier) {
		return Modifier.isPrivate(modifier) ? Accesser.PRIVATE : Modifier
				.isProtected(modifier) ? Accesser.PROTECTED : Modifier
				.isPublic(modifier) ? Accesser.PUBLIC : Accesser.NONE;
	}

	public static Object getPrimaryObject(String type, String value) {
		if (type.equals("Boolean") || type.equals("boolean")) {
			return new Boolean(value);
		} else if (type.equals("Character") || type.equals("char")) {
			return value.charAt(0);
		} else if (type.equals("Byte") || type.equals("byte")) {
			return new Byte(value);
		} else if (type.equals("Short") || type.equals("short")) {
			return new Short(value);
		} else if (type.equals("Integer") || type.equals("int")) {
			return new Integer(value);
		} else if (type.equals("Long") || type.equals("long")) {
			return new Long(value);
		} else if (type.equals("Float") || type.equals("float")) {
			return new Float(value);
		} else if (type.equals("Double") || type.equals("double")) {
			return new Double(value);
		} else if (type.equals("String")) {
			return value;
		} else {
			throw new IllegalArgumentException("型" + type + "はプライマリな型ではありません");
		}
	}

	public static String[] getPrimaryTypes() {
		String[] list = new String[] { "Boolean", "Character", "Byte", "Short",
				"Integer", "Long", "Float", "Double", "String" };

		return list;
	}
}
