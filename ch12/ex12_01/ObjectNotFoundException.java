package ex12_01;

public class ObjectNotFoundException extends Exception {
	final Object target;

	public ObjectNotFoundException(Object target) {
		super(target.toString());
		this.target = target;
	}

	public Object getTarget() {
		return target;
	}

	public String toString() {
		return "NotFound : " + target.toString();
	}
}
