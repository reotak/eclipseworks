package ex16_11;

public class NinjaPlayer extends Player {
	boolean start = false;
	String word = "";
	volatile int callCount = 0;
	Game game;

	public void play(Game game) {
		this.game = game;

		for (int i = 0; i < 3; i++) {
			new Bunshin().start();
		}

		game.start();
		start = true;

		while (callCount < 1701);

		game.iAmWinner();
	}

	private class Bunshin extends Thread {
		public void run() {

			while (!start);
			while (callCount < 1701) {
				game.call(word);
				callCount++;
				word += "a";
			}
		}
	}
}
