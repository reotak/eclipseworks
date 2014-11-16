package ex03_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest {

	@Test
	public void testRide() {
		PassengerVehicle pv = new PassengerVehicle(4);

		pv.ride();
		assertEquals(1, pv.getNumOfPassenger());
		pv.ride(3);
		assertEquals(4, pv.getNumOfPassenger());
		assertTrue(!pv.ride());
		assertEquals(4, pv.getNumOfPassenger());
		assertTrue(!pv.ride(2));
		assertEquals(4, pv.getNumOfPassenger());

		try {
			pv.ride(-2);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}


	@Test
	public void testSeat() {
		PassengerVehicle pv1 = new PassengerVehicle("Hanako", 2);
		assertEquals(2, pv1.getNumOfSeat());

		PassengerVehicle pv2 = null;
		try {
			pv2 = new PassengerVehicle("Hanako", -2);
			fail();
		} catch (IllegalArgumentException e) {

		}

		pv2 = new PassengerVehicle("Hanako", 3);
		assertEquals(pv2.getNumOfSeat(), 3);
	}

	@Test
	public void testVehicleId() {
		int id = PassengerVehicle.getVehicleId();

		// pv を生成することでVehicleIdを増加させる
		PassengerVehicle pv = new PassengerVehicle("Hanako", 3);
		assertTrue(pv != null); // 警告解除のためにpvを参照しておく
		assertEquals(id + 1, PassengerVehicle.getVehicleId());

	}
}
