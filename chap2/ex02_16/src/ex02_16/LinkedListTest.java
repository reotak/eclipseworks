package ex02_16;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testCountList() {
		LinkedList list = new LinkedList(1);
		try {
		assertEquals(1, list.countList(1000));

		list.setNext(new LinkedList(2));
		assertEquals(2, list.countList(1000));

		list.getNext().setNext(list);

		} catch (Exception e) {
			fail();
		}

		try {
			list.countList(1000);
			fail();
		} catch (InfinityLoopException e) {

		} catch(Exception e) {
			fail();
		}
	}

}
