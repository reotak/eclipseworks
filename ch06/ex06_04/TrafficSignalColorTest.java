package ex06_04;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrafficSignalColorTest {

	@Test
	public void test() {
		TrafficSignalColor blue = TrafficSignalColor.BLUE;

		assertEquals("blue", blue.getColor().getName());
		assertEquals("交差点へ進入しても良い", blue.getColor().getMean());
	}

}
