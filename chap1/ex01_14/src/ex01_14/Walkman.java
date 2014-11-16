package ex01_14;

public class Walkman {
	// テープの情報を現した文字列
	private String tape = "abcdefg";
	// テープの位置情報
	private int tapeCursol;

	public void setTape(String tape) {
		this.tape = tape;
		this.tapeCursol = 0;
	}

	public void fowardTape() {
		if (tapeCursol >= tape.length() - 1)
			tapeCursol = 0;
		else
			tapeCursol++;
	}

	public char listenTerminal1() {
		return tape.charAt(tapeCursol);
	}
}
