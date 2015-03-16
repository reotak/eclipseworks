package ex20_01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte2 {
	public static void translate(InputStream in, OutputStream out, byte from, byte to) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

	public static void main(String[] args) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(new File("ex20_01/TranslateByte2.java")));
		OutputStream out = new BufferedOutputStream(System.out);
		translate(in, out, (byte)'b', (byte)'B');
		in.close();
		out.flush();
		out.close();
	}
}
