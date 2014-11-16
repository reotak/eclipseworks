package ex02_02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	LinkedList list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList("Hello", new LinkedList("World"));
	}

	@Test
	public void test() {
		assertEquals("Hello", list.getData());
		assertEquals("World", list.getNext().getData());
		assertNull(list.getNext().getNext());
	}

}
