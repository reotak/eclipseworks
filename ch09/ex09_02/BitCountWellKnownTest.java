package ex09_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitCountWellKnownTest {

	@Test
	public void test() {
		assertEquals(0, BitCountWellKnown.bitCount(0x00000000));
		assertEquals(8, BitCountWellKnown.bitCount(0x11111111));
		assertEquals(32, BitCountWellKnown.bitCount(0xffffffff));
		assertEquals(4, BitCountWellKnown.bitCount(0x0000000f));
		assertEquals(4, BitCountWellKnown.bitCount(0xf0000000));
	}

}
