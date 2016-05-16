package report.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamicElementInfo {

	private Map<String, Class<?>> elements = new HashMap<String, Class<?>>();

	public DynamicElementInfo add(String name, Class<?> type) {
		elements.put(name, type);
		return this;
	}

	public Map<String, Class<?>> getMap() {
		return elements;
	}

	public Set<String> getSet() {
		return elements.keySet();
	}

}
