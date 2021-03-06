package intepret;

import java.lang.reflect.Type;
import java.util.List;

public interface IConstructor {

	abstract java.lang.reflect.Constructor<?> getReflectConstructor();

	public abstract Class<?> getCls();

	public abstract Type[] getArgTypes();

	public abstract List<String> getArgTypesAsString();

	public abstract String getName();

	public abstract String toString();

}