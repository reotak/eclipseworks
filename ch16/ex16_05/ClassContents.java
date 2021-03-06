package ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);

			printMembers(c);

		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Class<?> c) {
		Class<?> sc = c.getSuperclass();
		List<Member> scFields = new ArrayList<Member>();
		List<Member> scConstructors = new ArrayList<Member>();
		List<Member> scMethods = new ArrayList<Member>();
		
		if (sc != null) {
			System.out.println("SuperClass: " + sc.toString());
			scFields = arrayToList(sc.getFields());
			printMembers(scFields);
			scConstructors = arrayToList(sc.getConstructors());
			printMembers(scConstructors);
			scMethods = arrayToList(sc.getMethods());
			printMembers(scMethods);
		}
		
		System.out.println(c);

		List<Member> fields = new ArrayList<Member>();
		for (Member m : c.getDeclaredFields()) {
			if (!scFields.contains(m)) {
				fields.add(m);
			}
		}
		printMembers(fields);

		List<Member> constructors = new ArrayList<Member>();
		for (Member m : c.getDeclaredConstructors()) {
			if (!scConstructors.contains(m)) {
				constructors.add(m);
			}
		}
		printMembers(constructors);

		List<Member> methods = new ArrayList<Member>();
		for (Member m : c.getDeclaredMethods()) {
			if (!scMethods.contains(m)) {
				methods.add(m);
			}
		}
		printMembers(methods);
	}

	private static List<Member> arrayToList(Member[] members) {
		List<Member> list = new ArrayList<Member>();

		for (Member m : members) {
			list.add(m);
		}
		return list;
	}

	private static void printMembers(List<Member> mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}

			if (m instanceof Field) {
				for (Annotation a : ((Field)m).getAnnotations()) {
					String decl = a.toString();
					System.out.print("  ");
					System.out.println(strip(decl, "java.lang."));
				}
			}

			if (m instanceof Method) {
				for (Annotation a : ((Method)m).getAnnotations()) {
					String decl = a.toString();
					System.out.print("  ");
					System.out.println(strip(decl, "java.lang."));
				}
			}

			String decl = m.toString();
			System.out.print("  ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	public static String strip(String str, final String stripChars) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {

			boolean isMatch = true;
			for (int j = 0; j < stripChars.length(); j++) {
				if (i + j < str.length()) {
					if (str.charAt(i + j) != stripChars.charAt(j)) {
						isMatch = false;
						break;
					}
				} else {
					isMatch = false;
					break;
				}
			}

			if (isMatch) {
				i += stripChars.length() - 1;
			} else {
				result += str.charAt(i);
			}
		}

		return result;
	}

}
