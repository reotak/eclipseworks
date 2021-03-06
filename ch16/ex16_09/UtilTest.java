package ex16_09;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testInsertString() {
		assertEquals("Hello, World, .", Util.insertString(", ", "Hello", "World", "."));

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(Integer.valueOf(i));
		}
		assertEquals("0+1+2+3+4+5+6+7+8+9", Util.insertString("+", list));
	}

}
