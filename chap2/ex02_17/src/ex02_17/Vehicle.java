package ex02_17;

public class Vehicle {

	// 次の乗り物としての識別番号
	private static int vehicleId = 0;
	// 車としての識別番号
	private int machineId;

	private double speed;
	private double rad;
	private String ownerName;

	public static final int TURN_LEFT = 1;
	public static final int TURN_RIGHT = -1;

	Vehicle() {
		this("");
	}

	Vehicle(String ownerName) {
		this.ownerName = ownerName;
		vehicleId++;
	}

	public static int getVehicleId() {
		return vehicleId;
	}

	public static int getLeatestVehicleId() {
		return vehicleId - 1;
	}

	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	public void stop() {
		this.speed = 0.0d;
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

	public void turn(double rad) {
		this.rad += rad;
	}

	public void turn(int leftOrRight) {
		if (leftOrRight == TURN_LEFT) {
			turn(0.2d);
		} else {
			turn(-0.2d);
		}
	}

	public String toString() {
		String result = "";
		result += "Rad :" + this.getRad() + "\n";
		result += "Speed :" + this.getSpeed() + "\n";
		result += "Owner :" + this.getOwnerName() + "\n";
		result += "MachineId :" + this.getMachineId() + "\n";

		return result;
	}
}
