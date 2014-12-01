package ex13_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testCountChar() {
		assertEquals(1, StringUtil.countChar("Hello, World.", 'e'));
		assertEquals(10, StringUtil.countChar("aaa aaa aaa a", 'a'));
		assertEquals(2, StringUtil.countChar("aaa aaa\n aa\na a", '\n'));
	}
}
