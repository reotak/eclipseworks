package ex03_01;

public class PassengerVehicle extends Vehicle {
	private final int seatCount;
	private int passengerCount;

	PassengerVehicle(int seat) {
		super("");
		this.seatCount = seat;
		passengerCount = 0;
	}

	PassengerVehicle(String ownerName, int seat) {
		super(ownerName);

		if (seat <= 0) {
			throw new IllegalArgumentException("seat <= 0");
		}
		this.seatCount = seat;
		passengerCount = 0;
	}

	public int getNumOfSeat() {
		return seatCount;
	}

	public int getNumOfPassenger() {
		return passengerCount;
	}

	// ride a human
	public boolean ride() {
		return ride(1);
	}

	// ride count of passenger human
	public boolean ride(int passenger) {
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

	public static void main(String[] args) {
		PassengerVehicle lightCar = new PassengerVehicle("Takahashi", 4);

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
