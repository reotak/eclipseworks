package ex12_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObjectNotFoundExceptionTest {

	@Test
	public void test() {
		ObjectNotFoundException e = new ObjectNotFoundException("Target");
		assertEquals("Target", e.getTarget());
	}
}
