package ex06_04;

public enum TrafficSignalColor {
	BLUE("blue", "交差点へ進入しても良い"),
	YELLOW("yellow", "可能な限り交差点へ進入するな"),
	RED("red", "交差点へ進入してはならない");

	Color color;
	TrafficSignalColor(String name, String mean) {
		this.color = new Color(name, mean);
	}

	Color getColor() {
		return color;
	}
}

class Color {
	private final String name;
	private final String mean;

	public Color(String name, String mean) {
		this.name = name;
		this.mean = mean;
	}

	public String getName() {
		return name;
	}

	public String getMean() {
		return mean;
	}
}