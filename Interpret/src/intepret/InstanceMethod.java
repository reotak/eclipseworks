package intepret;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class InstanceMethod {
	private final Method m;
	private final Object target;
	private final String name;
	private final String returnType;
	private final List<String> argTypes = new ArrayList<String>();

	public InstanceMethod(Method m, Object target) {
		m.setAccessible(true);

		this.m = m;
		this.target = target;

		name = m.getName();
		returnType = Util.typeToString(m.getGenericReturnType());

		for (Type param : m.getGenericParameterTypes()) {
			argTypes.add(Util.typeToString(param));
		}
	}

	public final String getName() {
		return name;
	}

	public final String getReturnType() {
		return returnType;
	}

	public final List<String> getArgTypes() {
		return argTypes;
	}

	public final String getArgTypesAsString() {
		return Util.insertString(", ", argTypes);
	}

	public boolean isEqualArgTypes(String argTypesAsString) {
		if (argTypesAsString == null) {
			argTypesAsString = "";
		}

		String tmp = Util.insertString(", ", argTypes);
		return argTypesAsString.equals(tmp);
	}

	public Object invoke(Object[] values) throws Throwable {
		m.setAccessible(true);

		try {
			return m.invoke(target, values);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	public boolean isVoidMethod() {
		if (returnType.equals("void")) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return returnType + " " + name + "(" + Util.insertString(", ", argTypes) + ")";
	}

	public boolean isEqualSignature(Method method) {
		if (!name.equals(method.getName())) {
			return false;
		}

		if(argTypes.size() != method.getGenericParameterTypes().length) {
			return false;
		}

		int i = 0;
		for (Type t : method.getGenericParameterTypes()) {
			if (!Util.typeToString(t).equals(argTypes.get(i++))) {
				return false;
			}
		}
		return true;
	}
}
