package ex03_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class YTest {

	@Test
	public void test() {
		Y y = new Y();
		assertEquals(0xff00, y.mask(0xffff));

		X x = new Y();
		assertEquals(0xff00, x.mask(0xffff));
	}
}
