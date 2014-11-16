package ex01_15;

interface Lookup {
	Object find(String name);
	public boolean add(String name, Object value);
	boolean remove(String name);
	boolean remove(Object value);
}
