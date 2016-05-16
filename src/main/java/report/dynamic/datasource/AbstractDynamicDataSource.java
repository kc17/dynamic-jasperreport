package report.dynamic.datasource;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

public abstract class AbstractDynamicDataSource<T> extends JRAbstractBeanDataSource {

	protected Collection<T> data;
	protected Iterator<T> iterator;
	protected T currentBean;
	protected Set<String> dynamicColumnNames;

	public AbstractDynamicDataSource(Collection<T> beanCollection, Set<String> dynamicColumnNames) {
		super(true);
		this.data = beanCollection;
		if (this.data != null) {
			this.iterator = this.data.iterator();
		}
		this.dynamicColumnNames = dynamicColumnNames;
	}

	@Override
	public void moveFirst() throws JRException {
		if (this.data != null) {
			this.iterator = this.data.iterator();
		}
	}

	@Override
	public boolean next() throws JRException {
		boolean hasNext = false;
		if (this.iterator != null) {
			hasNext = this.iterator.hasNext();
			if (hasNext) {
				this.currentBean = this.iterator.next();
			}
		}
		return hasNext;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (dynamicColumnNames.contains(getPropertyName(jrField))) {
			Object responseValue = getDynamicFieldValue(jrField);
			return (responseValue != null) ? responseValue : getDefaultResponseValue(jrField.getValueClass());
		} else {
			return super.getFieldValue(currentBean, jrField);
		}
	}

	public abstract Object getDynamicFieldValue(JRField jrField);

	public abstract Object getDefaultResponseValue(Class<?> type);

}
