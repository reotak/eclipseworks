package ex01_08;

public class TestPoint {

	public static void main(String[] args) {
		Point p1 = new Point();
		Point p2 = new Point();

		p1.x = 10;
		p1.y = 20;

		p2.move(p1);
		printPoint(p1, "p1");
		printPoint(p2, "p2");
	}

	public static void printPoint(Point p, String prefix) {
		System.out.println((prefix != null ? prefix + " :" : "") + "x->" + p.x + ", y->" + p.y);
	}

}
