package ex13_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testCountChar() {
		assertEquals(1, StringUtil.countChar("Hello, World.", 'e'));
		assertEquals(10, StringUtil.countChar("aaa aaa aaa a", 'a'));
		assertEquals(2, StringUtil.countChar("aaa aaa\n aa\na a", '\n'));
	}

	@Test
	public void testCountSpecialChar() {
		assertEquals(0, StringUtil.countSpecialChar("Hello, World."));
		assertEquals(2, StringUtil.countSpecialChar("aaa aaa\n aa\na a"));
		assertEquals(8, StringUtil.countSpecialChar("\n\t\b\r\f\\\'\""));
	}

	@Test
	public void testDelimitedStrings() {
		String[] ss = StringUtil.delimitedStrings("(+ (* a b)  (* c d))", '(', ')');
		assertEquals(ss[0], "(+ (* a b)");
		assertEquals(ss[1], "(+ (* a b)  (* c d)");
		assertEquals(ss[2], "(+ (* a b)  (* c d))");
		assertEquals(ss[3], "(* a b)");
		assertEquals(ss[4], "(* a b)  (* c d)");
		assertEquals(ss[5], "(* a b)  (* c d))");
		
		String[] ss2 = StringUtil.delimitedStrings("Hello, World.", '(', ')');
		assertEquals(0, ss2.length);
	}
}
