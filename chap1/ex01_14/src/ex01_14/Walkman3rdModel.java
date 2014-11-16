package ex01_14;

public class Walkman3rdModel extends Walkman2ndModel {
	private char mic1 = '\0';
	private char mic2 = '\0';

	public void talk1(char c) {
		mic1 = c;
	}

	public void talk2(char c) {
		mic2 = c;
	}

	public char listenTerminal1() {
		char result;
		if (mic2 == '\0') {
			result = super.listenTerminal1();
		} else {
			result = mic2;
			mic2 = '\0';
		}

		return result;
	}

	public char listenTerminal2() {
		char result;
		if (mic1 == '\0') {
			result = super.listenTerminal2();
		} else {
			result = mic1;
			mic1 = '\0';
		}

		return result;
	}
}
