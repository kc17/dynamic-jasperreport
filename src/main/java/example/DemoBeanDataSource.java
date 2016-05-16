package example;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRField;
import report.dynamic.datasource.AbstractDynamicDataSource;

public class DemoBeanDataSource extends AbstractDynamicDataSource<DemoBean> {

	private static Map<Class<?>, Object> defaultValueMap = new HashMap<Class<?>, Object>();
	static {
		defaultValueMap.put(Integer.class, 0);
		defaultValueMap.put(String.class, "");
	}

	public DemoBeanDataSource(Collection<DemoBean> beanCollection, Set<String> dynamicColumnNames) {
		super(beanCollection, dynamicColumnNames);
	}

	@Override
	public Object getDynamicFieldValue(JRField jrField) {
		String originalName = getPropertyName(jrField);
		List<AdditionalBean> data = currentBean.getAdditionalInfo();
		for (AdditionalBean bean : data) {
			if (originalName.equals(bean.getInnerName())) {
				return bean.getInnerValue();
			}
		}
		return null;
	}

	@Override
	public Object getDefaultResponseValue(Class<?> type) {
		return defaultValueMap.get(type);
	}

}
