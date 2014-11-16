package ex10_04;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitCountDoVerTest {

	@Test
	public void testBitCount() {
		assertEquals(0, BitCountDoVer.bitCount(0x00000000));
		assertEquals(8, BitCountDoVer.bitCount(0x11111111));
		assertEquals(32, BitCountDoVer.bitCount(0xffffffff));
		assertEquals(4, BitCountDoVer.bitCount(0x0000000f));
		assertEquals(4, BitCountDoVer.bitCount(0xf0000000));
	}
}
