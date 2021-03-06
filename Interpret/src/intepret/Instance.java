package intepret;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Instance implements IInstance {
	private final String instanceId;
	private final Class<?> cls;
	private final Object obj;
	private final boolean isArray;
	private List<InstanceField> fields;
	private List<InstanceMethod> methods;

	public Instance(Instance ins) {
		instanceId = ins.instanceId;
		cls = ins.cls;
		obj = ins.obj;
		isArray = ins.isArray;
		createInstanceFields();
		createInstanceMethods();
	}

	public Instance(String instanceId, Class<?> cls)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		this.instanceId = instanceId;
		this.cls = cls;
		this.isArray = cls.isArray();
		if (this.isArray == true) {
			throw new IllegalArgumentException("配列型はサイズ指定のコンストラクタで呼び出してください");
		}
		this.obj = cls.newInstance();
		createInstanceFields();
		createInstanceMethods();
	}

	public Instance(String instanceId, IConstructor con, Object[] objects)
			throws Throwable {
		this(instanceId, con.getCls(), con.getReflectConstructor(), objects);
	}

	private Instance(String instanceId, Class<?> cls, Constructor<?> con,
			Object[] args) throws Throwable {
		this.instanceId = instanceId;
		this.cls = cls;
		this.isArray = cls.isArray();
		if (this.isArray) {
			throw new IllegalArgumentException("配列型はサイズ指定のコンストラクタで呼び出してください");
		}

		con.setAccessible(true);
		try {
		if (args == null || args.length == 0) {
			this.obj = con.newInstance();
		} else {
			this.obj = con.newInstance(args);
		}
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}

		createInstanceFields();
		createInstanceMethods();
	}

	public Instance(String instanceId, Class<?> cls, Class<?>[] argClss,
			Object[] args) throws Throwable {
		this.instanceId = instanceId;
		this.cls = cls;
		this.isArray = cls.isArray();
		if (this.isArray) {
			throw new IllegalArgumentException("配列型はサイズ指定のコンストラクタで呼び出してください");
		}

		// get constructor
		Constructor<?> con;
		if (argClss == null || argClss.length == 0) {
			con = cls.getDeclaredConstructor();
		} else {
			con = cls.getDeclaredConstructor(argClss);
		}

		// get object
		con.setAccessible(true);
		try {
			if (args == null || args.length == 0) {
				this.obj = con.newInstance();
			} else {
				this.obj = con.newInstance(args);
			}
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}

		createInstanceFields();
		createInstanceMethods();
	}

	public Instance(String instanceId, Class<?> cls, int[] size) {
		this.instanceId = instanceId;
		this.obj = Array.newInstance(cls, size);

		this.cls = this.obj.getClass();
		this.isArray = this.cls.isArray();
		if (isArray == false) {
			throw new IllegalArgumentException("配列型が生成できませんでした");
		}

		createInstanceFields();
		createInstanceMethods();
	}

	public List<InstanceField> getFields() {
		return fields;
	}

	public InstanceField getField(String name) {
		if (name == null) {
			throw new IllegalArgumentException("nameがnullです");
		}
		if (name.length() == 0) {
			throw new IllegalArgumentException("nameが空です");
		}

		for (InstanceField field : fields) {
			if (name.equals(field.getName())) {
				return field;
			}
		}
		// 指定された名前のフィールドが見つからなかった
		throw new IllegalArgumentException("nameの名前を持つフィールドが見つかりません");
	}

	public List<InstanceMethod> getMethods() {
		return methods;
	}

	public InstanceMethod getMethod(String name, String argTypesAsString) {
		if (name == null) {
			throw new IllegalArgumentException("nameがnullです");
		}
		if (name.length() == 0) {
			throw new IllegalArgumentException("nameが空です");

		}
		if (argTypesAsString == null) {
			argTypesAsString = "";
		}

		for (InstanceMethod method : methods) {
			if (method.getName().equals(name)
					&& method.isEqualArgTypes(argTypesAsString)) {
				return method;
			}
		}
		// 指定された名前・引数型のメソッドが見つからなかった
		throw new IllegalArgumentException("指定されたシグニチャを持つフィールドが見つかりません");
	}

	public String getInstanceId() {
		return instanceId;
	}

	public String getCls() {
		return cls.getSimpleName();
	}

	public Object getObject() {
		return obj;
	}

	private void createInstanceFields() {
		fields = new ArrayList<InstanceField>();

		if (isArray == false) {
			for (Field f : cls.getDeclaredFields()) {

				try {
					fields.add(new InstanceField(f, obj));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					assert false : "フィールドの設定に失敗しました";
				}
			}
		} else {
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				fields.add(new InstanceField(i, obj, cls.getComponentType()));
			}
		}
	}

	private void createInstanceMethods() {
		methods = new ArrayList<InstanceMethod>();

		for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
			for (Method m : c.getDeclaredMethods()) {
				if (!this.isContainsMethod(m)) {
					methods.add(new InstanceMethod(m, obj));
				}
			}
		}

	}

	private boolean isContainsMethod(Method m) {
		for (InstanceMethod method : methods) {
			if (method.isEqualSignature(m)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String result = getInstanceId() + "\n";

		for (InstanceField f : getFields()) {
			result += "\t" + f.toString() + "\n";
		}

		for (InstanceMethod m : getMethods()) {
			result += "\t" + m.toString() + "\n";
		}

		return result;
	}
}
