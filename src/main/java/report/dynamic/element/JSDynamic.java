package report.dynamic.element;

public class JSDynamic extends JSElement {

	public JSDynamic(JSColor color, JSElementType elementType) {
		super("", null, color, elementType);
	}

	@Override
	public boolean isDynamic() {
		return true;
	}

}
