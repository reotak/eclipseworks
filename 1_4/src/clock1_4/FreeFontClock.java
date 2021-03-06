package clock1_4;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;


public class FreeFontClock extends Frame implements ActionListener {
	private final int maxWidth = 2048;
	private final int maxHeight = 1200;
	private int width = 900, height = 300; // frame全体のサイズ
	private int whiteSpace = 25; // 上下左右の空白領域
	private int titleSpace = 100; // 上方向のタイトルバーの領域

	private MenuItem propertyMenu = null;
	private SettingDialog settingDialog = null;

	private final String defaultFontName;
	private final int defaultFontSize = 72;
	private final Color defaultFontColor;
	private final Color defaultGroundColor;

	public static void main(String[] args) {
		FreeFontClock clock = new FreeFontClock();
		clock.run();
	}

	public FreeFontClock() {
		defaultFontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[0].getName();
		defaultFontColor = Color.BLACK;
		defaultGroundColor = Color.WHITE;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});

		initMenuBar();

		this.setSize(width, height);
		this.setVisible(true);
	}

	private void initMenuBar() {
		MenuBar mb = new MenuBar();
		Menu m = new Menu("設定");
		propertyMenu = new MenuItem("プロパティ");
		m.add(propertyMenu);
		propertyMenu.addActionListener(this);

		mb.add(m);
		this.setMenuBar(mb);
	}

	private void run() {
		for (;;) {
			repaint(); // 再描写する

			// スリープを行う
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new InternalError(e.toString());
			}
		}
	}

	// 現在時刻を取得し時計を描画する
	public void paint(Graphics g) {
		Image img = createImage(maxWidth, maxHeight);
		Graphics buf = img.getGraphics();

		// 時刻を取得
		String time = timeToString(Calendar.getInstance());

		String name = defaultFontName;
		int size = defaultFontSize;
		Color fontColor = defaultFontColor;
		Color groundColor = defaultGroundColor;

		// 設定ダイアログから現在の設定を取得
		if (settingDialog != null) {
			name = settingDialog.getFontName();
			size = settingDialog.getFontSize();
			fontColor = settingDialog.getFontColor();
			groundColor = settingDialog.getGroundColor();
		}

		// フォントを設定
		Font font = createFont(g.getFont(), name, size);
		buf.setFont(font);

		// フォントからサイズを計算しなおす
		FontMetrics fo = buf.getFontMetrics();
		width = fo.stringWidth(time) + whiteSpace * 2;
		height = fo.getHeight() + titleSpace + whiteSpace;

		// bufをバックグラウンドカラーで塗りつぶす
		buf.setColor(groundColor);
		buf.fillRect(0, 0, width, height);


		// フォントの色を設定
		buf.setColor(fontColor);

		// 時刻を書き込む
		buf.drawString(time, whiteSpace, height - whiteSpace);
		g.drawImage(img, 0, 0, this);

		// resize
		this.setSize(width, height);

		if (this.isVisible() == false) {
			this.setVisible(true);
		}
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

	private Font createFont(Font defaultFont, String name, int size) {
		if (name == null) {
			name = defaultFont.getName();
		}
		if (size < 0) {
			size = defaultFont.getSize();
		}

		return new Font(name, Font.PLAIN, size);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void actionPerformed(ActionEvent e) {
		if (propertyMenu == e.getSource()) {
			if (settingDialog == null) { // craete
				settingDialog = new SettingDialog(new Frame(), "設定",
						defaultFontName, defaultFontSize, defaultFontColor, defaultGroundColor);

				settingDialog.setVisible(true);
			} else {
				if (settingDialog.isVisible() == false) {
					settingDialog.setVisible(true);
				}
			}
		}
	}

}
