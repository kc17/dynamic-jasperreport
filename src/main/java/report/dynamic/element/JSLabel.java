package report.dynamic.element;

public class JSLabel extends JSElement {

	public JSLabel(JSColor color) {
		this("", color);
	}

	public JSLabel(String text, JSColor color) {
		super(text, String.class, color, JSElementType.LABEL);
	}

}
