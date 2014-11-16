package ex01_15;

public class SimpleLookup implements Lookup{
	private String[] names;
	private Object[] values;

	public void initSimpleLookup() {
		names = new String[10];
		values = new Object[10];
	}

	public Object find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return values[i];
		}
		return null;
	}

	public boolean add(String name, Object value) {
		for (int i = 0; i < names.length; i++) {
			if (names[i] == null) {
				names[i] = name;
				values[i] = value;
				return true;
			}
		}

		return false;
	}

	public boolean remove(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i] == name) {
				names[i] = null;
				values[i] = null;

				return true;
			}
		}

		return false;
	}

	public boolean remove(Object value) {
		for (int i = 0; i < values.length; i++) {
			if (values[i].equals(value)) {
				names[i] = null;
				values[i] = null;

				return true;
			}
		}

		return false;
	}

	public String toString() {
		String result = "";
		for (int i = 0; (i < names.length) && (i < values.length); i++) {
			result += ((names[i] != null) ? names[i] : "-") + " : ";
			result += ((values[i] != null) ? values[i].toString() : "-") + "\n";
		}

		return result;
	}

}
