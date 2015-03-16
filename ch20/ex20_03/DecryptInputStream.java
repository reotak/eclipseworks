package ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

	static final byte key = (byte)0xFF;

	public DecryptInputStream(InputStream in) {
		super(in);
	}

	public int read() throws IOException {
		int b = super.read();
		if (b == -1) {
			return -1;
		} else {
			return byteToIntAsUnsignedByte(decrypt((byte)b));
		}
	}

	public int read(byte[] buf, int off, int len) throws IOException {
		int nread = super.read(buf, off, len);
		int last = off + nread;
		for (int i = off; i < last; i++) {
			buf[i] = decrypt(buf[i]);
		}

		return nread;
	}

	private static byte decrypt(byte b) {
		return (byte)(b ^ key);
	}

	private static int byteToIntAsUnsignedByte(byte b) {
		return b >= 0 ? b : 256 + b;
	}
}
