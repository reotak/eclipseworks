package ex16_11;

public class SimplePlayer extends Player {

	public void play(Game game) {
		String word = "";

		game.start();

		for (int i = 0; i < 1701; i++) {
			game.call(word);
			word += "a";
		}

		game.iAmWinner();
	}

}
