package ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Attr(DataInputStream in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		ObjectInputStream objin = new ObjectInputStream(in);
		value = objin.readObject();
	}
	public String getName() {
		return name;
	}

	public Object getValue() {
		return name;
	}


	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}

	public void outputDataOutputStream(DataOutputStream out) throws IOException {
		out.writeUTF(name);
		ObjectOutputStream objout = new ObjectOutputStream(out);
		objout.writeObject(value);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Attr attr = new Attr("hello", new Double(0.25d));
		attr.outputDataOutputStream(new DataOutputStream(new FileOutputStream("attr.dat")));

		Attr attr2 = new Attr(new DataInputStream(new FileInputStream("attr.dat")));
		System.out.println(attr2.toString());
	}
}
