package intepret;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class InstanceCreater {
	public static List<intepret.IConstructor> getConstructorList(String className) throws ClassNotFoundException {
		return getConstructorList(Class.forName(className));
	}

	public static List<IConstructor> getConstructorList(Class<?> cls) {
		List<intepret.IConstructor> cs = new ArrayList<intepret.IConstructor>();
		for (Constructor<?> c : cls.getDeclaredConstructors()) {
			cs.add(new intepret.Constructor(c, c.getGenericParameterTypes()));
		}

		return cs;
	}

	public static IInstance createInstance(String instanceId, intepret.IConstructor con, Object[] args) throws Throwable {
		return new Instance(instanceId, con, args);
	}

	public static IInstance createInstance(String instanceId, String cls, int[] size) throws ClassNotFoundException {
		Class<?> c = Class.forName(cls);

		return new Instance(instanceId, c, size);
	}
}
