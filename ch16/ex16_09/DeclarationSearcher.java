package ex16_09;

import static ex16_09.Util.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class DeclarationSearcher {


	public static ClassFormat getClassFormat(String className) {
		Class<?> c;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.err.println("Class Not Found");
			return null;
		}

		return getClassFormat(c, 0);
	}

	public static ClassFormat getClassFormat(Class<?> c, int tabDepth) {
		List<FieldFormat> fields = new ArrayList<FieldFormat>();
		for (Field f : c.getDeclaredFields()) {
			fields.add(new FieldFormat(
				f.getName(),
				typeToString(f.getGenericType()),
				modifierToString(f.getModifiers())));
		}

		List<MethodFormat> methods = new ArrayList<MethodFormat>();
		for (Method m : c.getDeclaredMethods()) {
			List<String> params = new ArrayList<String>();
			for (Type t : m.getGenericParameterTypes()) {
				params.add(typeToString(t));
			}

			List<String> exceptions = new ArrayList<String>();
			for (Type t : m.getGenericExceptionTypes()) {
				exceptions.add(typeToString(t));
			}
			methods.add(new MethodFormat(
					m.getName(),
					typeToString(m.getGenericReturnType()),
					modifierToString(m.getModifiers()),
					params,
					exceptions));
		}

		List<String> interfaces = new ArrayList<String>();
		for (Type t: c.getGenericInterfaces()) {
			interfaces.add(typeToString(t));
		}

		List<ClassFormat> innerClasses = new ArrayList<ClassFormat>();
		for (Class<?> inner : c.getDeclaredClasses()) {
			innerClasses.add(getClassFormat(inner, tabDepth + 1));
		}

		ClassFormat cf = new ClassFormat(
				typeToString(c),
				modifierToString(c.getModifiers(), true),
				typeToString(c.getSuperclass()),
				interfaces,
				innerClasses,
				fields,
				methods,
				tabDepth);

		return cf;
	}

	public static void main(String []args) {
		// interface
		System.out.println(getClassFormat("java.awt.ActiveEvent").toString());
		// simple class
		System.out.println(getClassFormat("java.lang.Integer").toString());
		// using generic class
		System.out.println(getClassFormat("java.util.ArrayList").toString());
		// for test
		System.out.println(getClassFormat("ex16_09.ForTest_GenericClass").toString());
	}
}
