package ex22_13;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class AttributeReaderTest {

	@Test
	public void doubleTest() throws IOException {
		Attributed attr = AttributeReader.readAttr(new InputStreamReader(new ByteArrayInputStream("\nhello\ntest=1701.1701\n\n".getBytes())));

		assertEquals("test", attr.getName());
		assertEquals(new Double(1701.1701), attr.getValue());
	}

	@Test
	public void stringTest() throws IOException {
		Attributed attr = AttributeReader.readAttr(new InputStreamReader(new ByteArrayInputStream("\nhello\ntest=test\n\n".getBytes())));

		assertEquals("test", attr.getName());
		assertEquals("test", attr.getValue());
	}

	@Test(expected=IOException.class)
	public void illegalNameTest() throws IOException {
		AttributeReader.readAttr(new InputStreamReader(new ByteArrayInputStream("\nhello\ntest=\n\n".getBytes())));
	}

	@Test(expected=IOException.class)
	public void illegalAttrTest() throws IOException {
		AttributeReader.readAttr(new InputStreamReader(new ByteArrayInputStream("\nhello\n=test\n\n".getBytes())));
	}

}
