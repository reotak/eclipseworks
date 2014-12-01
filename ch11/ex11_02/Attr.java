package ex11_02;

public class Attr<K, V> {
	private final K name;
	private V value = null;

	public Attr(K name) {
		this.name = name;
	}

	public Attr(K name, V value) {
		this.name = name;
		this.value = value;
	}

	public K getName() {
		return name;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V newValue) {
		V oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name.toString() + "='" + value.toString() + "'";
	}
}
