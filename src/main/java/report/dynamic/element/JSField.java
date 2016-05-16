package report.dynamic.element;

public class JSField extends JSElement {

	public JSField(String text, JSColor color) {
		this(text, String.class, color);
	}

	public JSField(String text, Class<?> type, JSColor color) {
		super(text, type, color, JSElementType.FIELD);
	}

}
