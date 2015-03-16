package clock2_1;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JFrame;


public class Clock extends JFrame {
	private final int maxWidth = 2048;
	private final int maxHeight = 1200;
	private int width = 900, height = 300; // frame全体のサイズ
	private int whiteSpace = 25; // 上下左右の空白領域

	public static void main(String[] args) {
		Clock clock = new Clock(); // start this program
		clock.run();
	}

	public Clock() {
		setSize(width, height);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	private void run() {
		for (;;) {
			repaint(); // 再描写する

			// スリープを行う
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new InternalError();
			}
		}
	}

	// 現在時刻を取得し時計を描画する
	public void paint(Graphics g) {
		// ダブルバッファリング用イメージ
		Image img = createImage(maxWidth, maxHeight);
		Graphics buf = img.getGraphics();

		// 時刻を取得
		String time = timeToString(Calendar.getInstance());

		// 時刻を書き込む
		buf.drawString(time, whiteSpace, height - whiteSpace);
		g.drawImage(img, 0, 0, this);
	}

	// 時刻を受け取り文字列にして返す
	private String timeToString(Calendar cal) {
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		String result = "";
		// 各ディスプレイに値を与えて再描写
		result += Integer.toString(hour   / 10);
		result += Integer.toString(hour   % 10);
		result += ":";
		result += Integer.toString(minute / 10);
		result += Integer.toString(minute % 10);
		result += ":";
		result += Integer.toString(second / 10);
		result += Integer.toString(second % 10);
		return(result);
	}

	public void update(Graphics g) {
		paint(g);
	}
}
