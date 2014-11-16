package ex02_11;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testToString() {
		LinkedList list = new LinkedList(1);
		list.setNext(new LinkedList(2.1d));
		list.getNext().setNext(new LinkedList("Hello, World"));

		String success = "1 -> 2.1 -> Hello, World";

		assertEquals(list.toString(), success);
	}
}
