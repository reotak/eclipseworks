package ex09_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitCountTest {

	@Test
	public void testBitCount() {
		assertEquals(0, BitCount.bitCount(0x00000000));
		assertEquals(8, BitCount.bitCount(0x11111111));
		assertEquals(32, BitCount.bitCount(0xffffffff));
		assertEquals(4, BitCount.bitCount(0x0000000f));
		assertEquals(4, BitCount.bitCount(0xf0000000));
	}

}
