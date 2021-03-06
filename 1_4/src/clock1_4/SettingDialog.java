package clock1_4;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingDialog extends Dialog implements WindowListener {
	private String fontName;
	private int fontSize = 72;
	private Color fontColor;
	private Color groundColor;

	private Choice fontNameChoice = null;
	private Choice fontSizeChoice = null;
	private Choice fontColorChoice = null;
	private Choice groundColorChoice = null;

	public SettingDialog(Frame frame, String title, String fontName, int fontSize, Color fontColor, Color groundColor) {
		super(frame, title);
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.groundColor = groundColor;

		this.setSize(400, 200);
		this.addWindowListener(this);

		GridBagLayout layout =new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		this.setLayout(layout);

		Label fontNameLabel = new Label("Font Name: ");
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontNameLabel, c);
		this.add(fontNameLabel);

		fontNameChoice = createFontNameChoice();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontNameChoice, c);
		this.add(fontNameChoice);

		Label fontSizeLabel = new Label("Font Size: ");
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontSizeLabel, c);
		this.add(fontSizeLabel);

		fontSizeChoice = createFontSizeChoice();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontSizeChoice, c);
		this.add(fontSizeChoice);

		Label fontColorLabel = new Label("Font Color: ");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(fontColorLabel, c);
		this.add(fontColorLabel);


		fontColorChoice = createFontColorChoice();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(fontColorChoice, c);
		this.add(fontColorChoice);

		Label groundColorLabel = new Label("Ground Color: ");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(groundColorLabel, c);
		this.add(groundColorLabel);

		groundColorChoice = createGroundColorChoice();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(groundColorChoice, c);
		this.add(groundColorChoice);

		Button okButton = new Button("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refrectNowSetting();
			}
		});
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(okButton, c);
		this.add(okButton);

		Button cancelButton = new Button("CANCEL");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(cancelButton, c);
		this.add(cancelButton);
	}

	public void windowClosing(WindowEvent event) {
		this.dispose();
	}
	public void windowOpened(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowActivated(WindowEvent e) {
	}
	public void windowDeactivated(WindowEvent e) {
	}

	private Choice createFontNameChoice() {
		Choice c = new Choice();

		for (Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
			c.addItem(f.getName());
		}
		return c;
	}

	private Choice createFontSizeChoice() {
		Choice c = new Choice();
		int[] sizes = new int[]{72, 144, 288, 576};
		for (Integer s : sizes) {
			c.addItem(s.toString());
		}
		return c;
	}

	private Choice createFontColorChoice() {
		Choice c = new Choice();
		c.addItem("Black");
		c.addItem("Blue");
		c.addItem("Red");
		c.addItem("Yellow");
		c.addItem("White");
		return c;
	}

	private Choice createGroundColorChoice() {
		Choice c = new Choice();
		c.addItem("White");
		c.addItem("Black");
		c.addItem("Blue");
		c.addItem("Red");
		c.addItem("Yellow");
		return c;
	}

	public String getFontName() {
		if (this.isVisible()) {
			return fontNameChoice.getSelectedItem();
		} else {
			return fontName;
		}
	}

	public int getFontSize() {
		if (this.isVisible()) {
			try {
				return Integer.parseInt(fontSizeChoice.getSelectedItem());
			} catch (Exception e) {
				assert false : "フォントサイズに数字としてパースできない値が設定されていました";
				return -1;
			}
		} else {
			return fontSize;
		}
	}

	public Color getFontColor() {
		if (this.isVisible()) {
			return colorStringToColor(fontColorChoice.getSelectedItem());
		} else {
			return fontColor;
		}
	}

	public Color getGroundColor() {
		if (this.isVisible()) {
			return colorStringToColor(groundColorChoice.getSelectedItem());
		} else {
			return groundColor;
		}
	}

	private void refrectNowSetting() {
		fontName = fontNameChoice.getSelectedItem();
		try {
			fontSize = Integer.parseInt(fontSizeChoice.getSelectedItem());
		} catch (Exception e) {
			assert false : "フォントサイズに数字としてパースできない値が設定されていました";
			fontSize = -1;
		}
		fontColor = colorStringToColor(fontColorChoice.getSelectedItem());
		groundColor = colorStringToColor(groundColorChoice.getSelectedItem());
	}

	private Color colorStringToColor(String s) {
		switch(s) {
		case "White" :
			return Color.WHITE;
		case "Black" :
			return Color.BLACK;
		case "Blue" :
			return Color.BLUE;
		case "Red" :
			return Color.RED;
		case "Yellow" :
			return Color.YELLOW;
		default:
			return Color.BLACK;
		}
	}

}
