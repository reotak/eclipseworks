// getNumOfSeatやgetNumOfPassengerはこのクラスが処理を規定しているためfinalとすべきである。
// rideも同様にこのクラスが処理を規定しているためfinalとすべきである。

package ex03_08;

public class PassengerVehicle extends Vehicle implements Cloneable {
	private final int seatCount;
	private int passengerCount;

	public PassengerVehicle(int seat, EnergySource e) {
		super("", e);
		this.seatCount = seat;
		passengerCount = 0;
	}

	public PassengerVehicle(String ownerName, int seat, EnergySource e) {
		super(ownerName, e);

		if (seat <= 0) {
			throw new IllegalArgumentException("seat <= 0");
		}
		this.seatCount = seat;
		passengerCount = 0;
	}

	public final int getNumOfSeat() {
		return seatCount;
	}

	public final int getNumOfPassenger() {
		return passengerCount;
	}

	// ride a human
	public final boolean ride() {
		return ride(1);
	}

	// ride count of passenger human
	public final boolean ride(int passenger) {
		if (passenger < 0) {
			throw new IllegalArgumentException();
		}

		// can not ride
		if (this.passengerCount + passenger > seatCount) {
			return false;
		}

		this.passengerCount += passenger;

		return true;
	}

	public String toString() {
		String str = super.toString() + "\n";
		str += "NumOfSeat :" + getNumOfSeat() + "\n";
		str += "NumOfPassenger :" + getNumOfPassenger();

		return str;
	}

	public PassengerVehicle clone() throws CloneNotSupportedException {
		return (PassengerVehicle)super.clone();
	}

	public static void main(String[] args) {
		PassengerVehicle lightCar = new PassengerVehicle("Takahashi", 4, new GasTank());

		lightCar.ride(2);
		System.out.println(lightCar.getNumOfSeat());
		System.out.println(lightCar.toString());

		lightCar.ride(2);
		System.out.println(lightCar.getNumOfSeat());
		System.out.println(lightCar.toString());

		lightCar.ride(2);
		System.out.println(lightCar.getNumOfSeat());
		System.out.println(lightCar.toString());
	}
}
