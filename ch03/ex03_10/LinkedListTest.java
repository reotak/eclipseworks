package ex03_10;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testClone() {
		LinkedList list = new LinkedList(1);

		list.setNext(new LinkedList(2));
		list.getNext().setNext(new LinkedList(3));

		LinkedList clone = list.clone();

		// データは同様のものを参照してることを確認
		assertEquals(1, clone.getData());
		assertEquals(2, clone.getNext().getData());
		assertEquals(3, clone.getNext().getNext().getData());

		// リストは別なものであることを確認
		assertTrue(list != clone);
		assertTrue(list.getNext() != clone.getNext());
		assertTrue(list.getNext().getNext() != clone.getNext().getNext());
	}
}
