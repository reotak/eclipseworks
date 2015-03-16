package ex20_03;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

	static final byte key = (byte)0xFF;

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}

	public void write(int b) throws IOException {
		super.write(byteToIntAsUnsignedByte(encrypt((byte)b)));
	}

	public void write(byte[] buf, int off, int len) throws IOException {
		byte[] tmp = new byte[buf.length];

		for (int i = off; i < off + len; i++) {
			tmp[i] = encrypt(buf[i]);
		}

		super.write(tmp, off, len);
	}

	private static byte encrypt(byte b) {
		return (byte)(b ^ key);
	}

	private static int byteToIntAsUnsignedByte(byte b) {
		return b >= 0 ? b : 256 + b;
	}

	public static void main(String[] args) throws IOException {
		DecryptInputStream in = new DecryptInputStream(System.in);
		EncryptOutputStream out = new EncryptOutputStream(new FileOutputStream("tmp.dat"));

		for (int i = 0; i < 10; i++) {
			out.write(in.read());
		}
		in.close();
		out.flush();
		out.close();
	}
}
