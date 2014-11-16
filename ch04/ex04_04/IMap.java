package ex04_04;

public interface IMap extends ICollection {
	Object add(Object key);
	Object find(Object key);
	boolean delete(Object key);
}
