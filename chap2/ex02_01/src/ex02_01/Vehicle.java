package ex02_01;

public class Vehicle {
	private double speed;
	private double rad;
	private String ownerName;

	Vehicle(String ownerName) {
		this.ownerName = ownerName;
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
}
