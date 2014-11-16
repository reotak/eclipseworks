package ex03_08;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testCloneUseGas() {

		GasTank gas = new GasTank();
		gas.setGas(20);

		Vehicle v1 = new Vehicle("Yoshida", gas);
		int idAfterCreateV1 = Vehicle.getVehicleId();

		Vehicle v2 = null;
		try {
			v2 = v1.clone();
		} catch (Exception e) {
			fail("Cloneに失敗しました：" + e);
		}

		assertEquals(idAfterCreateV1, Vehicle.getVehicleId());
		assertEquals(v1.getOwnerName(), v2.getOwnerName());
		assertEquals(v1.getMachineId(), v2.getMachineId());
		assertEquals(new Double(v1.getRad()), new Double(v2.getRad()));
		assertEquals(new Double(v1.getSpeed()), new Double(v2.getSpeed()));
		assertNotEquals(v1.getEnergySource(), v2.getEnergySource());
		assertEquals(v1.getEnergySource().getCharge(), v2.getEnergySource().getCharge());
	}

	@Test
	public void testCloneUseBattery() {

		Battery battery  = new Battery();
		battery.setBattery(20);

		Vehicle v1 = new Vehicle("Yoshida", battery);
		int idAfterCreateV1 = Vehicle.getVehicleId();

		Vehicle v2 = null;
		try {
			v2 = v1.clone();
		} catch (Exception e) {
			fail("Cloneに失敗しました：" + e);
		}

		assertEquals(idAfterCreateV1, Vehicle.getVehicleId());
		assertEquals(v1.getOwnerName(), v2.getOwnerName());
		assertEquals(v1.getMachineId(), v2.getMachineId());
		assertEquals(new Double(v1.getRad()), new Double(v2.getRad()));
		assertEquals(new Double(v1.getSpeed()), new Double(v2.getSpeed()));
		assertNotEquals(v1.getEnergySource(), v2.getEnergySource());
		assertEquals(v1.getEnergySource().getCharge(), v2.getEnergySource().getCharge());
	}

}
