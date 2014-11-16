package ex01_15;

public class SimpleLookupMain {

	public static void main(String[] args) {
		SimpleLookup sl = new SimpleLookup();
		sl.initSimpleLookup();
		sl.add("_0", "Hello");
		sl.add("_1", "World");

		System.out.println(sl.toString());

		sl.remove("_0");
		System.out.println(sl.toString());

		Object tmp = "tmp";
		sl.add("_2", tmp);
		System.out.println(sl.toString());

		sl.remove(tmp);
		System.out.println(sl.toString());
	}

}
