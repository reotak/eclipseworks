package ex01_14;

public class WalkmanMain {

	public static void main(String[] args) {
		System.out.println("--------- Walkman -------");

		Walkman w1 = new Walkman();

		w1.setTape("This is music1.");
		for (int i = 0; i < 20; i++) {
			System.out.print(w1.listenTerminal1());
			w1.fowardTape();
		}
		System.out.println();

		System.out.println("--------- 2ndModel -------");
		Walkman2ndModel w2 = new Walkman2ndModel();
		w2.setTape("Music2");
		for (int i = 0; i < 20; i++) {
			System.out.println("Terminal1 :" + w2.listenTerminal1());
			System.out.println("Terminal2 :" + w2.listenTerminal2());
			w2.fowardTape();
		}
		System.out.println();

		System.out.println("--------- 3rdModel -------");
		Walkman3rdModel w3 = new Walkman3rdModel();
		w3.setTape("3333333333333333");
		for (int i = 0; i < 20; i++) {
			System.out.println("Terminal1 :" + w3.listenTerminal1());
			System.out.println("Terminal2 :" + w3.listenTerminal2());

			if (i % 4 == 0) {
				w3.talk1('1');
			} else if (i % 2 == 0) {
				w3.talk2('2');
			}

			w2.fowardTape();
		}
		System.out.println();

	}
}
