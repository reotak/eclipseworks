package ex04_01;

class GasTank implements EnergySource {

	private int gas;


	public GasTank() {
		gas = 100;
	}

	public boolean empty() {
		return gas <= 0;
	}

	public void setGas(int gas) {
		this.gas = gas;
	}
}
