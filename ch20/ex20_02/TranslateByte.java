package ex20_02;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TranslateByte extends FilterInputStream {
	private byte from;
	private byte to;

	public TranslateByte(InputStream in, byte from, byte to) {
		super(in);

		this.from = from;
		this.to = to;
	}

	public int read() throws IOException {
		int b = super.read();
		if (b == -1) {
			return -1;
		} else {
			return translate(b);
		}
	}

	public int read(byte[] buf, int off, int len) throws IOException {
		int nread = super.read(buf, off, len);
		int last = off + nread;
		for (int i = off; i < last; i++) {
			buf[i] = (byte)translate(buf[i]);
		}

		return nread;
	}

	private int translate(int b) {
		return b == from ? to : b;
	}

	public static void main(String[] args) throws IOException {
		TranslateByte tb = new TranslateByte(System.in, (byte) 'b', (byte) 'B');

		for (int i = 0; i < 2; i ++) {
			System.out.write(tb.read());
		}

		for (int i = 0; i < 2; i ++) {
			System.out.write(tb.read());
		}

		tb.close();
	}
}
