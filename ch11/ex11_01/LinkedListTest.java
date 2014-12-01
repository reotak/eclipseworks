package ex11_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testAddValue() {
		LinkedList<Integer> list = new LinkedList<Integer>(1);

		list.add(2, 100);
		list.add(3, 100);

		// データは同様のものを参照してることを確認
		assertEquals(new Integer(1), list.getValue());
		assertEquals(new Integer(2), list.getNext().getValue());
		assertEquals(new Integer(3), list.getNext().getNext().getValue());
	}

	@Test
	public void testClone() {
		LinkedList<Integer> list = new LinkedList<Integer>(1);

		list.setNext(new LinkedList<Integer>(2));
		list.getNext().setNext(new LinkedList<Integer>(3));

		LinkedList<Integer> clone = list.clone();

		// データは同様のものを参照してることを確認
		assertEquals(new Integer(1), clone.getValue());
		assertEquals(new Integer(2), clone.getNext().getValue());
		assertEquals(new Integer(3), clone.getNext().getNext().getValue());

		// リストは別なものであることを確認
		assertTrue(list != clone);
		assertTrue(list.getNext() != clone.getNext());
		assertTrue(list.getNext().getNext() != clone.getNext().getNext());
	}

}
