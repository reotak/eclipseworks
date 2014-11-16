package ex03_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorAttrTest {

	@Test
	public void testHashCode() {
		ColorAttr c1 = new ColorAttr("LowTone", "gray");
		ColorAttr c3 = new ColorAttr("LowTone", "gray");

		assertEquals(c1.hashCode(), c3.hashCode());
	}

	@Test
	public void testEqualsColorAttr() {
		ColorAttr c1 = new ColorAttr("LowTone", "gray");
		ColorAttr c2 = new ColorAttr("HighTone", "white");
		ColorAttr c3 = new ColorAttr("LowTone", "gray");

		assertTrue(!c1.equals(c2));
		assertTrue(c1.equals(c3));
	}

}
