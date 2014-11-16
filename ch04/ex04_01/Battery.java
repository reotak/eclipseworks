package ex04_01;

class Battery implements EnergySource {
	private int battery;

	Battery() {
		battery = 100;
	}

	public boolean empty() {
		return battery <= 0;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}
}
