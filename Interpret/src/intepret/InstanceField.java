package intepret;

import java.lang.reflect.Field;

public class InstanceField {
	private final Field f;
	private final Object target;
	private final String name;
	private final String type;
	private String value;

	public InstanceField(Field f, Object target) throws IllegalArgumentException, IllegalAccessException {
		f.setAccessible(true);

		this.f = f;
		this.target = target;

		name = f.getName();
		type = Util.typeToString(f.getGenericType());

		Object tmp = f.get(target);
		if (tmp != null) {
			value = tmp.toString();
		} else {
			value = "NULL";
		}
	}

	public final String getName() {
		return name;
	}

	public final String getValue() {
		return value;
	}

	public final String getType() {
		return type;
	}

	public final void reflesh() {
		Object tmp = null;
		try {
			f.setAccessible(true);
			tmp = f.get(target);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			assert false : "値が設定できませんでした";
		}
		if (tmp != null) {
			value = tmp.toString();
		} else {
			value = "NULL";
		}
	}

	public void setValue(Object value)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException {
		f.setAccessible(true);
		f.set(target, value);
		if (value != null) {
			this.value = value.toString();
		}  else {
			this.value = "NULL";
		}
	}

	public String toString() {
		return name + " -> " + value;
	}
}