package ex20_01;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte3 {
	public static void translate(InputStream in, OutputStream out, byte from, byte to) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

	public static void main(String[] args) throws IOException {
		InputStream in = new ByteArrayInputStream(new byte[] {(byte)'a', (byte)'b', (byte)'c'});
		OutputStream out = new ByteArrayOutputStream();
		translate(in, out, (byte)'b', (byte)'B');
		in.close();
		out.flush();
		System.out.println(out.toString());
		out.close();
	}
}
