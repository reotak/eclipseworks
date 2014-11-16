package ex02_15;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	Vehicle car = new Vehicle("Takahashi");

	@Test
	public void testChangeSpeed() {
		car.changeSpeed(40.0d);
		assertEquals(new Double(car.getSpeed()), new Double(40.0d));
	}

	@Test
	public void testStop() {
		car.changeSpeed(55.5d);
		car.stop();
		assertEquals(new Double(car.getSpeed()), new Double(0.0d));
	}

}
