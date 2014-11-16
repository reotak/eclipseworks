package sevenSegmentClock;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class DigitalClockSegments extends Frame {
	static DigitalClockSegments frame = null;

	static private final int height = 300, width = 900; // frame全体のサイズ
	static private final int whiteSpace = 25; // 上下左右の空白領域
	static private final int titleSpace = 25; // 上方向のタイトルバーの領域
	static private SevenSegmentDisplay disp; // 時計オブジェクト
	static private Calendar now; // 現在時刻

	public static void main(String[] args) {

		// create frame
		frame = new DigitalClockSegments();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});

		// 空白領域を考慮し、時計に割り当てられる領域で時計を生成する
		disp = new SevenSegmentDisplay(
				whiteSpace,
				titleSpace + whiteSpace,
				width - whiteSpace * 2,
				height - (titleSpace + whiteSpace * 2));

		// main loop
		while (true) {
			frame.repaint(); // 再描写する

			// スリープを行う
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new InternalError(e);
			}
		}
	}

	// 現在時刻を取得し時計を描画する
	public void paint(Graphics g) {
		setTime(); // 現在時刻を取得
		disp.draw(g, now); // 今の時間をディスプレイに描写する
	}

	public void update(Graphics g) {
		paint(g);
	}

	// 現在時刻を設定する
	static private void setTime() {
		now = Calendar.getInstance();
	}
}


