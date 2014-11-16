package ex04_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testILinkedList() {
		ILinkedList list = new LinkedList(1);

		list.setNext(new LinkedList(2));
		list.getNext().setNext(new LinkedList(3));

		// データは同様のものを参照してることを確認
		assertEquals(1, list.getData());
		assertEquals(2, list.getNext().getData());
		assertEquals(3, list.getNext().getNext().getData());
	}
}
