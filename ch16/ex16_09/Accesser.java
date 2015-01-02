package ex16_09;

enum Accesser {
	PUBLIC("public"),
	PROTECTED("protected"),
	PRIVATE("private"),
	NONE("");

	String name;
	Accesser(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
