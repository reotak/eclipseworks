package ex03_08;

public class Vehicle implements Cloneable{
	// 次の乗り物としての識別番号
	private static int vehicleId = 0;
	// 車としての識別番号
	private int machineId;

	private double speed;
	private double rad;
	private String ownerName;

	private EnergySource energy;

	public static final int TURN_LEFT = 1;
	public static final int TURN_RIGHT = -1;

	public Vehicle(EnergySource e) {
		this("", e);
	}

	public Vehicle(String ownerName, EnergySource e) {
		this.ownerName = ownerName;
		energy = e;
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

	public void start() {
		if (!energy.empty()) {
			speed = 10.0;
		} else {
			stop();
		}
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

	public final String getOwnerName() {
		return ownerName;
	}


	public final int getMachineId() {
		return machineId;
	}

	public EnergySource getEnergySource() {
		return energy;
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
		result += "MachineId :" + this.getMachineId();

		return result;
	}


	public Vehicle clone() throws CloneNotSupportedException {
		Vehicle v = (Vehicle)super.clone();
		v.energy = this.energy.clone();

		return v;
	}

	public static void main(String[] args) {
		String ownerName = "no name";

		if (args.length >= 1) {
			ownerName = args[0];
		}

		Vehicle v1 = new Vehicle(ownerName, new Battery());
		v1.start();
		v1.setRad(1.1d);
		System.out.println(v1.toString());
	}
}
