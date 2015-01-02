package ex16_09;

import java.util.List;

public class ForTest_GenericClass<T1, T2> {
	public static List<? extends List<Integer>> [][]testField;

	public static ForTest_GenericClass<? extends List<Integer>, ? extends String>[][] testMethod(ForTest_GenericClass<? extends List<Integer>, ? extends String>[][]... list)
			throws IllegalArgumentException {

		return list[0];
	}
}
