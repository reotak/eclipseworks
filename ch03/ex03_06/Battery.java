package ex03_06;

class Battery extends EnergySource {
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
