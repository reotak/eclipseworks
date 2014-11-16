package ex05_01;

public interface Attributed {
	public class Attr {
		private final String name;
		private Object value = null;

		public Attr(String name) {
			this.name = name;
		}

		public Attr(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return name;
		}

		public Object setValue(Object newValue) {
			Object oldVal = value;
			value = newValue;
			return oldVal;
		}

		public String toString() {
			return name + "='" + value + "'";
		}
	}

	void add (Attr newAttr);
	Attr find(String attrName);
	Attr remove(String attrName);
	java.util.Iterator<Attr> attrs();
}