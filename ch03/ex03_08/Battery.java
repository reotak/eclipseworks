package ex03_08;

public class Battery extends EnergySource implements Cloneable {
	private int battery;

	public Battery() {
		battery = 100;
	}

	public boolean empty() {
		return battery <= 0;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getCharge() {
		return battery;
	}

	public Battery clone() {
		Battery b = new Battery();
		b.battery = this.battery;

		return b;
	}
}
