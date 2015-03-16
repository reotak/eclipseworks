package ex21_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void popTest() {
		Stack<String> stack = new Stack<String>(10);
		assertEquals(null, stack.pop());

		stack.push("one");
		stack.push("two");
		stack.push("three");

		assertEquals("three", stack.peek());
		assertEquals("three", stack.pop());
		assertEquals("two", stack.pop());
		assertEquals("one", stack.pop());
		assertEquals(null, stack.pop());
	}

	@Test
	public void pushOverTest() {
		Stack<String> stack = new Stack<String>(3);

		stack.push("one");
		stack.push("two");
		stack.push("three");

		assertEquals(false, stack.push("four"));
	}
}
