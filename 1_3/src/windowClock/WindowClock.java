package windowClock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowClock extends Frame implements MouseListener,
		MouseMotionListener {
	private Window window;
	private ClockCanvas canvas;
	private PopupMenu popup;

	private String fontName = null;
	private int fontSize = 72;
	private Color fontColor = null;
	private Color groundColor = null;

	private int width = 400;
	private int height = 200;

	private int locationX = 200;
	private int locationY = 200;
	private Point startPoint = null;
	private Point nowPoint = null;

	static public void main(String[] args) {
		WindowClock frame = new WindowClock();
		frame.run();
	}

	public WindowClock() {
		window = new Window(this);

		// canvas
		canvas = new ClockCanvas();
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		// popup
		popup = createPopup();
		canvas.add(popup);

		window.add(canvas, "Center");

		window.setSize(width, height);
		window.setVisible(true);
	}

	public void run() {
		for (;;) {
			try {
				// set now setting
				canvas.setFontName(fontName);
				canvas.setFontSize(fontSize);
				canvas.setFontColor(fontColor);
				canvas.setGroundColor(groundColor);

				// paint
				canvas.repaint();

				// set size
				window.setSize(canvas.getWidth(), canvas.getHeight());

				// set location
				if (startPoint != null && nowPoint != null) {
					locationX += nowPoint.x - startPoint.x;
					locationY += nowPoint.y - startPoint.y;
					startPoint = null;
				}
				window.setLocation(locationX, locationY);

				window.toFront();
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}

	private PopupMenu createPopup() {
		PopupMenu pm = new PopupMenu();

		// ----------- add font menu ----------------
		Menu fontNameMenu = new Menu("font name");
		for (Font f : GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAllFonts()) {
			MenuItem fontNameItem = new MenuItem(f.getName());
			fontNameItem.addActionListener(new FontNameActionListener(this, f
					.getName()));
			fontNameMenu.add(fontNameItem);
		}
		pm.add(fontNameMenu);

		// ---------- add font size menu ------------
		Menu fontSizeMenu = new Menu("font size");
		int[] sizes = new int[] { 72, 144, 288, 576 };
		for (Integer s : sizes) {
			MenuItem fontSizeItem = new MenuItem(s.toString());
			fontSizeItem.addActionListener(new FontSizeActionListener(this, s));
			fontSizeMenu.add(fontSizeItem);
		}
		pm.add(fontSizeMenu);

		// ここで対応するカラーのリスト
		String[] colors = new String[] { "Black", "White", "Blue", "Red",
				"Yellow" };

		// ---------- add font color menu ------------
		Menu fontColorMenu = new Menu("font color");
		for (String color : colors) {
			MenuItem fontColorItem = new MenuItem(color);
			fontColorItem.addActionListener(new FontColorActionListener(this,
					colorStringToColor(color)));
			fontColorMenu.add(fontColorItem);
		}
		pm.add(fontColorMenu);

		// ---------- add ground color menu ------------
		Menu groundColorMenu = new Menu("ground color");
		for (String color : colors) {
			MenuItem groundColorItem = new MenuItem(color);
			groundColorItem.addActionListener(new GroundColorActionListener(
					this, colorStringToColor(color)));
			groundColorMenu.add(groundColorItem);
		}
		pm.add(groundColorMenu);

		MenuItem exitItem = new MenuItem("exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		pm.add(exitItem);

		return pm;
	}

	class FontNameActionListener implements ActionListener {
		private final String fontName;
		private final WindowClock clock;

		public FontNameActionListener(WindowClock clock, String fontName) {
			this.clock = clock;
			this.fontName = fontName;
		}

		public void actionPerformed(ActionEvent event) {
			clock.setFontName(fontName);
		}
	}

	class FontSizeActionListener implements ActionListener {
		private final int fontSize;
		private final WindowClock clock;

		public FontSizeActionListener(WindowClock clock, int fontSize) {
			this.clock = clock;
			this.fontSize = fontSize;
		}

		public void actionPerformed(ActionEvent event) {
			clock.setFontSize(fontSize);
		}
	}

	class FontColorActionListener implements ActionListener {
		private final Color fontColor;
		private final WindowClock clock;

		public FontColorActionListener(WindowClock clock, Color fontColor) {
			this.clock = clock;
			this.fontColor = fontColor;
		}

		public void actionPerformed(ActionEvent event) {
			clock.setFontColor(fontColor);
		}
	}

	class GroundColorActionListener implements ActionListener {
		private final Color groundColor;
		private final WindowClock clock;

		public GroundColorActionListener(WindowClock clock, Color groundColor) {
			this.clock = clock;
			this.groundColor = groundColor;
		}

		public void actionPerformed(ActionEvent event) {
			clock.setGroundColor(groundColor);
		}
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public void setFontSize(int size) {
		this.fontSize = size;
	}

	public void setFontColor(Color color) {
		this.fontColor = color;
	}

	public void setGroundColor(Color color) {
		this.groundColor = color;
	}

	private static Color colorStringToColor(String s) {
		if (s.equals("White")) {
			return Color.WHITE;
		} else if (s.equals("Black")) {
			return Color.BLACK;
		} else if (s.equals("Blue")) {
			return Color.BLUE;
		} else if (s.equals("Red")) {
			return Color.RED;
		} else if (s.equals("Yellow")) {
			return Color.YELLOW;
		} else {
			return Color.BLACK;
		}
	}

	// --------- mouse listener ------------------
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.show(canvas, e.getX(), e.getY());
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.show(canvas, e.getX(), e.getY());
		}

		// left Button released
		if (e.getButton() == MouseEvent.BUTTON1) {
			startPoint = null;
		}
	}

	// --------- mouse motion listener ----------
	public void mouseDragged(MouseEvent e) {

		if (startPoint == null) {
			startPoint = e.getPoint();
		}
		nowPoint = e.getPoint();
	}

	public void mouseMoved(MouseEvent e) {

	}
}
