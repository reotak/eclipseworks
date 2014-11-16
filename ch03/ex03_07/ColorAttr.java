package ex03_07;

public class ColorAttr extends Attr {
	private ScreenColor myColor;

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}

	public ColorAttr(String name) {
		this(name, "transparent");
	}

	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}

	public Object setValue(Object newValue) {
		// スーパークラスのsetValueを最初に行う
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}

	/** 値を記述ではなくScreenColor に設定する */
	public ScreenColor setValue(ScreenColor newValue) {
		// スーパークラスのsetValueを最初に行う
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	/** 変換された ScreenColor オブジェクトを返す */
	public ScreenColor getColor() {
		return myColor;
	}

	/** getValue()で得られる記述から ScreenColor を設定する */
	protected void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	public boolean equals(ColorAttr color) {
		return this.myColor.equals(color.getColor()) && (super.getName() == color.getName());
	}

	public int hashCode() {
		return super.getName().hashCode() + myColor.hashCode();
	}
}

// 「このようなクラスがある」と仮定するための仮実装
class ScreenColor {
	private Object color;
	public ScreenColor(Object color) {
		this.color = color;
	}

	public boolean equals(ScreenColor c) {
		return color.equals(c.getColor());
	}

	public int hashCode() {
		return color.hashCode();
	}

	private Object getColor() {
		return color;
	}
}
