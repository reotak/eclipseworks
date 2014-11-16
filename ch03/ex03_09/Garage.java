package ex03_09;

import ex03_08.Vehicle;

class Garage implements Cloneable {
	private Vehicle[] vehicles;

	public Garage(Vehicle[] vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle[] getVehilcles() {
		return vehicles;
	}

	public void setVehicles(Vehicle[] vehicles) {
		this.vehicles = vehicles;
	}

	public Garage clone() throws CloneNotSupportedException {
		Garage g = (Garage)super.clone();

		// deep copy
		g.vehicles = this.vehicles.clone();
		for (int i = 0; i < this.vehicles.length; i++) {
			if (this.vehicles[i] != null) {
				g.vehicles[i] = this.vehicles[i].clone();
			}
		}

		return g;
	}
}
