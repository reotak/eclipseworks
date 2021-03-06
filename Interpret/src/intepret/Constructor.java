package intepret;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Constructor implements IConstructor {
	final private java.lang.reflect.Constructor<?> con;
	final Type[] argTypes;
	final String name;

	public Constructor(java.lang.reflect.Constructor<?> con, Type[] argTypes) {
		this.con = con;
		this.argTypes = argTypes;

		// このコンストラクタを表す名前を生成
		String tmp = "";
		tmp += getCls().getSimpleName() + "(" ;

		List<String> types = new ArrayList<String>();
		for (Type type : argTypes) {
			types.add(Util.typeToString(type));
		}
		tmp += Util.insertString(", ", types) + ")";

		name = tmp;
	}

	public final java.lang.reflect.Constructor<?> getReflectConstructor() {
		return con;
	}

	public final Class<?> getCls() {
		return con.getDeclaringClass();
	}

	public final Type[] getArgTypes() {
		return argTypes;
	}

	public final List<String> getArgTypesAsString() {
		List<String> types = new ArrayList<String>();
		for (Type t : argTypes) {
			types.add(Util.typeToString(t));
		}
		return types;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
