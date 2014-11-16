package ex02_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {
	Vehicle bike = new Vehicle("Takahashi");

	@Test
	public void testGetSpeed() {
		bike.setSpeed(30.0d);
		assertEquals(new Double(30.0d), new Double(bike.getSpeed()));
	}

	@Test
	public void testGetRad() {
		bike.setRad(1.0d);
		assertEquals(new Double(1.0d), new Double(bike.getRad()));
	}

	@Test
	public void testGetOwnerName() {
		assertEquals("Takahashi", bike.getOwnerName());
	}

}
