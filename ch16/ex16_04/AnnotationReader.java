package ex16_04;

import java.lang.annotation.Annotation;

class AnnotationReader {

	public static void printAnnotation(Class<?> src) {
		for (Annotation ano : src.getAnnotations()) {
			System.out.println(ano.toString());
		}
	}

	public static void main(String[] args) {
		String className = "java.lang.annotation.Retention";
		try {
			printAnnotation(Class.forName(className));
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + className);
		}
	}
}
