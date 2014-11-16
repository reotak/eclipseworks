package ex03_06;

class GasTank extends EnergySource {

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
