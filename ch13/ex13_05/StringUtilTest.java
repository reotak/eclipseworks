package ex13_05;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

	@Test
	public void testTypeValueFormatReader() {
		String str = "Integer 5\nCharacter c\nFloat 5.27293\n";
		str += "Short 2222\nByte 111\nLong 123123123123123123\n";
		str += "Double 1.23545231245\n";

		ArrayList<Object> res = StringUtil.typeValueFromatReader(str);
		assertEquals(res.get(0).toString(), "5");
		assertEquals(res.get(1).toString(), "c");
		assertEquals(res.get(2).toString(), "5.27293");
		assertEquals(res.get(3).toString(), "2222");
		assertEquals(res.get(4).toString(), "111");
		assertEquals(res.get(5).toString(), "123123123123123123");
		assertEquals(res.get(6).toString(), "1.23545231245");
	}

	@Test
	public void testSeparateNumber() {
		String str = "Integer 5\nCharacter c\nFloat 5.27293\n";
		str += "Short 2222\nByte 111\nLong 123123123123123123\n";
		str += "Double 1.23545231245\n";

		String expect = "Integer 5\nCharacter c\nFloat 5.27293\n";
		expect += "Short 2,222\nByte 111\nLong 123,123,123,123,123,123\n";
		expect += "Double 1.23545231245\n";

		String res = StringUtil.separateNumber(str);
		assertEquals(expect, res);
	}



}
