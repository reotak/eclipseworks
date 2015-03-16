package ex22_12;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class AttributeReaderTest {

	@Test
	public void doubleTest() {
		Attributed attr = AttributeReader.readAttr(new InputStreamReader(new ByteArrayInputStream("\nhello\ntest=1701.1701\n\n".getBytes())));

		assertEquals("test", attr.getName());
		assertEquals(new Double(1701.1701), attr.getValue());
	}

	@Test
	public void stringTest() {
		Attributed attr = AttributeReader.readAttr(new InputStreamReader(new ByteArrayInputStream("\nhello\ntest=test\n\n".getBytes())));

		assertEquals("test", attr.getName());
		assertEquals("test", attr.getValue());
	}

}
