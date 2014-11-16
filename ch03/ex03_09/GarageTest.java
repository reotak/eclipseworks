package ex03_09;

import static org.junit.Assert.*;

import org.junit.Test;

import ex03_08.Battery;
import ex03_08.GasTank;
import ex03_08.Vehicle;

public class GarageTest {

	@Test
	public void testClone() {
		Vehicle[] vs = new Vehicle[2];

		GasTank gas = new GasTank();
		gas.setGas(20);
		vs[0] = new Vehicle("Yoshida", gas);
		vs[1] = new Vehicle("Yamada", new Battery());

		Garage g = new Garage(vs);

		assertTrue(g.getVehilcles().equals(vs));

		Garage g2 = null;
		try {
			g2 = g.clone();
		} catch (CloneNotSupportedException e) {
			fail();
		}

		assertNotEquals(g, g2);
		assertNotEquals(g.getVehilcles(), g2.getVehilcles());
		assertEquals(g.getVehilcles()[0].getOwnerName(), g2.getVehilcles()[0].getOwnerName());
		assertEquals(g.getVehilcles()[0].getEnergySource().getCharge(), g2.getVehilcles()[0].getEnergySource().getCharge());

		// change g data
		g.getVehilcles()[0].setOwnerName("NewOwner");
		gas.setGas(30);

		assertNotEquals(g.getVehilcles()[0].getOwnerName(), g2.getVehilcles()[0].getOwnerName());
		assertNotEquals(g.getVehilcles()[0].getEnergySource().getCharge(), g2.getVehilcles()[0].getEnergySource().getCharge());
	}

}
