package ex16_11;

import java.util.ArrayList;
import java.util.List;

public class Game {
	public static void main(String[] args) {
		String name; // クラス名
		while ((name = getNextPlayer()) != null) {
			try {
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();

				System.out.println(name + " Game start");
				Game game = new Game();
				player.play(game);
				game.reportScore(name);
			} catch (Exception e) {
				e.printStackTrace();
				reportException(name, e);
			}
		}
	}

	private static String[] players = new String[] {"ex16_11.SimplePlayer", "ex16_11.NinjaPlayer" };
	private static int nowPlayer = 0;


	private static String getNextPlayer() {
		if (nowPlayer < players.length) {
			return players[nowPlayer++];
		} else {
			return null;
		}
	}

	// ------ ゲームのルール ---------
	// * プレイヤーがstartを呼び出すとゲームが始まる
	// * プレイヤーはゲームに丁度1701個の単語を登録したときに、iAmWinnerを呼び出せば勝利条件を満たし、勝利できる
	// * プレイヤーはcallを呼び出し、単語を登録することができる
	// * 一つでも同じ単語が登録されている場合は、勝利条件を満たさない
	// * 勝利条件を満たしていないときに、iAmWinnerを呼び出すとプレイヤーの負けとなる。
	// * スコアはプレイヤーが負けた場合0点、勝った場合は、(100(ms) - 勝利まで掛かった時間(ms))点とする。ただし最低10点は与えられる。
	// * ゲームは同時に話かけられることにより、単語を聞き漏らすことがある。それはプレイヤーの責任となる。
	// * iAmWinnerが呼び出されることでゲームは終了する。（プレイヤーはplayを終了しなければならない）
	// * startを呼ばずに、他のメソッドを呼ぶと、プレイヤーの負けとなる。

	// ------ 以下、ゲームの実装 -----
	private long startTime;
	private long clearTime;

	private boolean isGameStarted = false;
	private boolean isPlayerWin = false;
	private boolean isPlayerCannotWin = false;

	private List<String> words = new ArrayList<String>();

	public void start() {
		isGameStarted = true;
		startTime = System.currentTimeMillis();
	}

	public String iAmWinner() {
		if (!isGameStarted) {
			isPlayerCannotWin = true;
		}

		clearTime = System.currentTimeMillis();

		// 勝利判定
		if (!isPlayerCannotWin && words.size() == 1701) {
			isPlayerWin = true;
		}

		if (isPlayerWin) {
			return "Player Win";
		} else {
			return "Player Lose";
		}
	}

	public void call(String word) {
		try {
			if (!isGameStarted) {
				isPlayerCannotWin = true;
			}

			// 勝てない条件を満たした
			if (words.contains(word)) {
				isPlayerCannotWin = true;
			}

			words.add(word);
		} catch (Exception e) {
			isPlayerCannotWin = true;
		}
	}

	private void reportScore(String name) {
		System.out.println(" Player :" + name);

		int score;
		if (isPlayerWin) {
			score = (int) (100 - (clearTime - startTime));
			if (score < 10) {
				score = 10;
			}
		} else {
			score = 0;
		}

		System.out.println(" Score : " + score);
	}

	private static void reportException(String name, Exception e) {
		System.out.println("Error");
		System.out.println(" Player :" + name);
		System.out.println(" Exception :" + e);
	}
}
