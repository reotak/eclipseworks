package ex11_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {

	@Test
	public void test() {
		Attr<String, Integer> attr = new Attr<String, Integer>("One", 1);

		assertEquals("One", attr.getName());
		assertEquals(new Integer(1), attr.getValue());
	}
}
