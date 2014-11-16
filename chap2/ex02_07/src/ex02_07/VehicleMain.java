package ex02_07;

public class VehicleMain {

	public static void main(String[] args) {

		Vehicle v1 = new Vehicle("Takahashi");
		v1.setRad(1.1d);
		v1.setSpeed(60.0d);
		System.out.println(vehicleToString(v1));

		Vehicle v2 = new Vehicle("Sato");
		v2.setRad(1.7d);
		v2.setSpeed(30.8d);
		System.out.println(vehicleToString(v2));

		Vehicle v3 = new Vehicle();
		v3.setOwnerName("Suzukii");
		v3.setRad(-2.3d);
		v3.setSpeed(0.0d);
		System.out.println(vehicleToString(v3));
	}

	public static String vehicleToString(Vehicle v) {
		String result = "";
		result += "Rad :" + v.getRad() + "\n";
		result += "Speed :" + v.getSpeed() + "\n";
		result += "Owner :" + v.getOwnerName() + "\n";
		result += "MachineId :" + v.getMachineId() + "\n";
		result += "NowVehicleId :" + Vehicle.getVehicleId();

		return result;
	}

}
