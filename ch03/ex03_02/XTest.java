package ex03_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class XTest {

	@Test
	public void test() {
		X x = new X();
		assertEquals(0x00ff, x.mask(0xffff));
		assertEquals(0x0034, x.mask(0x1234));
		assertEquals(0x0000, x.mask(0x0000));
	}

}
