package ex02_06;


public class LinkedListMain {

	public static void main(String[] args) {
		LinkedList list = new LinkedList(new Vehicle("Takahashi"));
		((Vehicle)list.getData()).setRad(0);
		((Vehicle)list.getData()).setSpeed(40);

		list.setNext(new LinkedList(new Vehicle("Yoshida")));
		((Vehicle)list.getNext().getData()).setRad(-0.2);
		((Vehicle)list.getNext().getData()).setSpeed(12);

		list.getNext().setNext(new LinkedList(new Vehicle("Yoshida")));
		((Vehicle)list.getNext().getNext().getData()).setRad(4.0d);
		((Vehicle)list.getNext().getNext().getData()).setSpeed(120.0d);


		LinkedList node = list;
		while(node != null) {
			System.out.println(vehicleToString((Vehicle)node.getData()));
			node = node.getNext();
		}
	}

	public static String vehicleToString(Vehicle v) {
		String result = "";
		result += "Rad :" + v.getRad() + "\n";
		result += "Speed :" + v.getSpeed() + "\n";
		result += "Owner :" + v.getOwnerName() + "\n";
		result += "MachineId :" + v.getMachineId() + "\n";
		result += "NowVehicleId :" + Vehicle.getVehicleId();

		return result;
	}

}
