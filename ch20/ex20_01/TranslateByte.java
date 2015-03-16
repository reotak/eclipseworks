package ex20_01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {
	public static void translate(InputStream in, OutputStream out, byte from, byte to) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("ex20_01/TranslateByte.java");
		OutputStream out = new FileOutputStream("tmp.txt");
		translate(in, out, (byte)'b', (byte)'B');
		in.close();
		out.close();
	}
}
