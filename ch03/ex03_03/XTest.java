package ex03_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class XTest {

	@Test
	public void test() {
		X x = new X();
		assertEquals(0x00ff, x.mask(0xffff));
	}

}
