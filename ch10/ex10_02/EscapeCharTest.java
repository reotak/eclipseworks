package ex10_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeCharTest {

	@Test
	public void testUnescape() {
		String s = EscapeChar.unescape("a\n\t\bb\r\f\\\'\"c");
		assertEquals("a\\n\\t\\bb\\r\\f\\\\\\'\\\"c", s);
	}
}
