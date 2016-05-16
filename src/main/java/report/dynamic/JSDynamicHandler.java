package report.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import report.dynamic.element.JSElement;

public class JSDynamicHandler {

	public static List<JSElement> insert(List<JSElement> elements, DynamicElementInfo info) {
		if (info == null) {
			return elements;
		}
		List<JSElement> newElements = new ArrayList<JSElement>();
		for (JSElement element : elements) {
			if (element.isDynamic()) {
				insert(newElements, info, element);
			} else {
				newElements.add(element);
			}
		}
		return newElements;
	}

	private static void insert(List<JSElement> newElements, DynamicElementInfo info, JSElement templateElement) {
		for (Entry<String, Class<?>> entry : info.getMap().entrySet()) {
			newElements.add(new JSElement(entry.getKey(), entry.getValue(), templateElement.getColor(),
					templateElement.getElementType()));
		}
	}
}
