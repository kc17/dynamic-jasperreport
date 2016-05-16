package example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import report.JSReportUtil;
import report.dynamic.DynamicElementInfo;
import report.dynamic.JasperDesignBuilder;

public class DemoRunner {

	private static DynamicElementInfo info = new DynamicElementInfo();
	static {
		info.add("inner 0", Integer.class);
		info.add("inner 1", Integer.class);
		info.add("inner 2", Integer.class);
		info.add("inner 3", Integer.class);
		info.add("inner 4", Integer.class);
		info.add("inner 5", Integer.class);
	}

	private Map<String, Object> updateParams(List<DemoBean> demoBeans) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parameter", "Hello");

		for (Entry<String, Class<?>> entry : info.getMap().entrySet()) {
			map.put(entry.getKey(), 0);
		}

		for (DemoBean bean : demoBeans) {
			for (AdditionalBean addition : bean.getAdditionalInfo()) {
				Integer value = addition.getInnerValue() + (Integer) map.get(addition.getInnerName());
				map.put(addition.getInnerName(), value);
			}

		}
		return map;
	}

	public void buid() throws Exception {

		List<DemoBean> demoBeans = DemoBeanFactory.list();
		Map<String, Object> params = updateParams(demoBeans);

		JasperDesign jasperDesign = new JasperDesignBuilder("blank.jrxml")
				.setPageHeader(DemoBeanTemplate.pageHeader, info)
				.setColumnHeader(DemoBeanTemplate.columnHeader, info)
				.setDetail(DemoBeanTemplate.detail, info)
				.build("demobean");

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		DemoBeanDataSource ds = new DemoBeanDataSource(demoBeans, info.getSet());

		JasperPrint jasperPrint = JSReportUtil.generateJSPrint(ds, params, jasperReport);
		JSReportUtil.generateReport(Arrays.asList(jasperPrint), new JRXlsxExporter(), "../../tmp/dynamic_demo.xlsx");
	}

	public static void main(String[] args) throws Exception {
		new DemoRunner().buid();
	}

}
