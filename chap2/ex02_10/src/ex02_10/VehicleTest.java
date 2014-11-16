package ex02_10;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() {
		Vehicle v1 = new Vehicle("Takahashi");
		v1.setRad(0.8d);
		v1.setSpeed(60.1d);

		String success = "";
		success += "Rad :0.8\n";
		success += "Speed :60.1\n";
		success += "Owner :Takahashi\n";
		success += "MachineId :0\n";
		assertEquals(v1.toString(), success);
	}

}
