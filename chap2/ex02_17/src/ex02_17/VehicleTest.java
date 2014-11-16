package ex02_17;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testTurn() {
		Vehicle v = new Vehicle("Takahashi");
		v.setRad(0);
		v.turn(0.5d);
		assertEquals(new Double(0.5d), new Double(v.getRad()));
		v.turn(Vehicle.TURN_RIGHT);
		assertEquals(new Double(0.3d), new Double(v.getRad()));
	}
}
