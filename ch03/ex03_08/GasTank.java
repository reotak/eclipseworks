package ex03_08;

public class GasTank extends EnergySource implements Cloneable {

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

	public int getCharge() {
		return gas;
	}

	public GasTank clone() {
		try {
			return (GasTank)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
