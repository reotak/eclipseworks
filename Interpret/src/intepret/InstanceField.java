package intepret;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class InstanceField {
	private final Field f;
	private final Object target;
	private final String name;
	private final String type;
	private final boolean isArray;


	public InstanceField(Field f, Object target) throws IllegalArgumentException, IllegalAccessException {
		f.setAccessible(true);

		this.f = f;
		this.target = target;

		name = f.getName();
		type = Util.typeToString(f.getGenericType());

		isArray = false;
	}

	public InstanceField(int index, Object target, Class<?> fieldType) {
		f = null;
		this.target = target;
		this.name = index + "";

		isArray = true;

		type = Util.typeToString(fieldType);
	}

	public final String getName() {
		return name;
	}

	public final String getValue() {
		Object tmp = null;
		try {
			if (isArray == false) {
				f.setAccessible(true);
				tmp = f.get(target);
			} else {
				tmp = Array.get(target, new Integer(name));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			assert false : "値が取得できませんでした";
		}
		if (tmp != null) {
			return tmp.toString();
		} else {
			return "NULL";
		}

	}

	public final String getType() {
		return type;
	}

	public void setValue(Object value)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException {
		if (isArray == false){
			f.setAccessible(true);
			f.set(target, value);
		} else {
			Array.set(target, new Integer(name), value);
		}
	}

	public String toString() {
		return name + " -> " + getValue().toString();
	}
}
