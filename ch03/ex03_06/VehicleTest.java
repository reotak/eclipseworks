package ex03_06;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testGasTank() {
		GasTank gas = new GasTank();
		Vehicle v = new Vehicle("Tanaka", gas);

		gas.setGas(100);
		v.start();
		assertEquals(new Double(10.0d), new Double(v.getSpeed()));

		gas.setGas(0);
		v.start();
		assertEquals(new Double(0.0d), new Double(v.getSpeed()));
	}

	@Test
	public void testBattery() {
		Battery battery = new Battery();
		Vehicle v = new Vehicle("Tanaka", battery);

		battery.setBattery(100);
		v.start();
		assertEquals(new Double(10.0d), new Double(v.getSpeed()));

		battery.setBattery(0);
		v.start();
		assertEquals(new Double(0.0d), new Double(v.getSpeed()));
	}

}
