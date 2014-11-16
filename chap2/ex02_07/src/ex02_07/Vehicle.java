package ex02_07;

public class Vehicle {
	// 次の乗り物としての識別番号
	private static int vehicleId = 0;
	// 車としての識別番号
	private int machineId;

	private double speed;
	private double rad;
	private String ownerName;

	public static int getVehicleId() {
		return vehicleId;
	}


	Vehicle() {
		this("");
	}

	Vehicle(String ownerName) {
		this.ownerName = ownerName;
		vehicleId++;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setRad(double rad) {
		this.rad = rad;
	}

	public double getRad() {
		return rad;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public int getMachineId() {
		return machineId;
	}
}