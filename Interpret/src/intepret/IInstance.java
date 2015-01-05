package intepret;

import java.util.List;

public interface IInstance {

	public abstract List<InstanceField> getFields();

	public abstract InstanceField getField(String name);

	public abstract List<InstanceMethod> getMethods();

	public abstract InstanceMethod getMethod(String name, String argTypesAsString);

	public abstract String getInstanceId();

	public abstract String getCls();

	public abstract Object getObject();

	public abstract String toString();

}