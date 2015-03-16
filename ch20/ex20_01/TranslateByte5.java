package ex20_01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte5 {
	public static void translate(InputStream in, OutputStream out, byte from, byte to) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(new FileInputStream("ex20_01/TranslateByte5.java"));
		OutputStream out = new DataOutputStream(System.out);
		translate(in, out, (byte)'b', (byte)'B');
		in.close();
		out.flush();
		out.close();
	}
}
