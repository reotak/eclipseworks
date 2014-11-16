package ex06_05;

public enum TrafficSignalColor {
	BLUE {
		Color getColor() {
			return new Color("blue", "交差点へ進入しても良い");
		}
	},
	YELLOW {
		Color getColor() {
			return new Color("yellow", "可能な限り交差点へ進入するな");
		}
	},
	RED {
		Color getColor() {
			return new Color("red", "交差点へ進入してはならない");
		}
	};

	abstract Color getColor();
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