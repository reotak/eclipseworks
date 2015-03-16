package ex22_14;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleListReaderTest {

	@Test
	public void simpleTest() {
		assertEquals(new Double(12.0), new Double(DoubleListReader.sum("0.1 0.2 0.3 0.4 1.1 2.2 3.3 4.4")));
	}

	@Test(expected=IllegalArgumentException.class)
	public void illegalTest() {
		DoubleListReader.sum("hello");
	}

	@Test(expected=IllegalArgumentException.class)
	public void newLineTest() {
		DoubleListReader.sum("1\n2\n");
	}
}
