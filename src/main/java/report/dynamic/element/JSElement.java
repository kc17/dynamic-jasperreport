package report.dynamic.element;

import java.awt.Color;

public class JSElement {

	final private String text;
	final private Class<?> type;
	final private JSColor color;
	final private JSElementType elementType;

	public JSElement(String text, Class<?> type, JSColor color, JSElementType elementType) {
		this.text = text;
		this.type = type;
		this.color = color;
		this.elementType = elementType;
	}

	public String getText() {
		return text;
	}

	public String getJSText() {
		if (isParameter()) {
			return String.format("$P{%s}", getText());
		} else if (isField()) {
			return String.format("$F{%s}", getText());
		} else if (isFunction()) {
			return getText();
		} else {
			return "\"" + getText() + "\"";
		}
	}

	public Class<?> getType() {
		return type;
	}

	public JSColor getColor() {
		return color;
	}

	public Color getBackcolor() {
		return color.getBackcolor();
	}

	public Color getForecolor() {
		return color.getForecolor();
	}

	public boolean isParameter() {
		return JSElementType.PARAMETER.equals(elementType);
	}

	public boolean isField() {
		return JSElementType.FIELD.equals(elementType);
	}

	public boolean isFunction() {
		return JSElementType.FUNCTION.equals(elementType);
	}

	public boolean isDynamic() {
		return false;
	}

	public JSElementType getElementType() {
		return elementType;
	}

}
