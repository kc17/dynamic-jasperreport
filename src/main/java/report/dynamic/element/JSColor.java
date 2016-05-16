package report.dynamic.element;

import java.awt.Color;

public class JSColor {

	private Color backcolor;
	private Color forecolor;

	public JSColor(Color backcolor, Color forecolor) {
		this.backcolor = backcolor;
		this.forecolor = forecolor;
	}

	public Color getBackcolor() {
		return backcolor;
	}

	public Color getForecolor() {
		return forecolor;
	}

}
