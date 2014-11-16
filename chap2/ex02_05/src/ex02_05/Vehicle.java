package ex02_05;

public class Vehicle {
	// 次の乗り物としての識別番号
	private static int vehicleId = 0;
	// 車としての識別番号
	private final int machineId;

	private double speed;
	private double rad;
	private String ownerName;

	Vehicle(String ownerName) {
		this.ownerName = ownerName;
		this.machineId = vehicleId;
		vehicleId++;
	}

	public static int getVehicleId() {
		return vehicleId;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setRad(double rad) {
		this.rad = rad;
	}
	public double getSpeed() {
		return speed;
	}

	public double getRad() {
		return rad;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public int getMachineId() {
		return machineId;
	}
}