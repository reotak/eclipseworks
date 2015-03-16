package ex20_01;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;

public class TranslateByte4 {
	public static void translate(InputStream in, OutputStream out, byte from, byte to) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

	public static void main(String[] args) throws IOException {
		PushbackInputStream in = new PushbackInputStream(new FileInputStream("ex20_01/TranslateByte4.java"));
		OutputStream out = new BufferedOutputStream(System.out);
		translate(in, out, (byte)'b', (byte)'B');

		in.unread('b');
		translate(in, out, (byte)'b', (byte)'B');

		in.close();
		out.flush();
		out.close();
	}
}
