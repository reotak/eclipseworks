package ex03_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class YTest {

	@Test
	public void test() {
		Y y = new Y();

		assertEquals(0xffff, y.mask(0xffff));
		assertEquals(0x1234, y.mask(0x1234));
		assertEquals(0x0000, y.mask(0x0000));
	}
}
