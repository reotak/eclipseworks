package windowClock;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Calendar;

public class ClockCanvas extends Canvas {
	int maxWidth = 2080;
	int maxHeight = 1200;

	private String fontName = null;
	private int fontSize = 72;
	private Color fontColor = null;
	private Color groundColor = null;

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		Image img = createImage(maxWidth, maxHeight);
		Graphics buf = img.getGraphics();

		// 時刻を取得
		String time = timeToString(Calendar.getInstance());

		// フォントを生成
		if (fontName == null) {
			fontName = g.getFont().getName();
		}
		Font font = new Font(fontName, Font.PLAIN, fontSize);
		buf.setFont(font);

		// フォントからサイズを計算しなおす
		FontMetrics fo = buf.getFontMetrics();
		int width = fo.stringWidth(time);
		int height = fo.getHeight();

		// bufをバックグラウンドカラーで塗りつぶす

		if (groundColor != null) {
			buf.setColor(groundColor);
		} else {
			buf.setColor(Color.WHITE);
		}
		buf.fillRect(0, 0, width, height);

		// 時刻を書き込む
		if (fontColor != null) {
			buf.setColor(fontColor);
		} else {
			buf.setColor(Color.BLACK);
		}
		buf.drawString(time, 0, height);

		// draw and resize
		g.drawImage(img, 0, 0, this);
		this.setSize(width, height);
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


	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	public void setGroundColor(Color groundColor) {
		this.groundColor = groundColor;
	}
}
