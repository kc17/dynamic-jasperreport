package report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class JSReportUtil {

	public static <T> JasperPrint generateJSPrint(List<T> beanCollection, Map<String, Object> params, String jrxmlName)
			throws JRException, IOException {
		try (InputStream in = JSReportUtil.class.getClassLoader().getResource("jrxml/" + jrxmlName).openStream();) {
			JasperReport jasperReport = JasperCompileManager.compileReport(in);
			return generateJSPrint(beanCollection, params, jasperReport);
		}
	}

	public static <T> JasperPrint generateJSPrint(List<T> beanCollection, Map<String, Object> params,
			JasperReport jasperReport) throws JRException {
		if (beanCollection.isEmpty()) {
			return JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
		} else {
			return generateJSPrint(new JRBeanCollectionDataSource(beanCollection), params, jasperReport);
		}
	}

	public static <T> JasperPrint generateJSPrint(JRAbstractBeanDataSource ds, Map<String, Object> params,
			JasperReport jasperReport) throws JRException {
		params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		return JasperFillManager.fillReport(jasperReport, params, ds);
	}

	public static File generateReport(List<JasperPrint> jasperPrints, JRAbstractExporter exporter, String reportName)
			throws JRException, IOException {

		File report = new File(reportName);
		try (FileOutputStream out = new FileOutputStream(report);) {
			setExportParameter(exporter);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
		}
		return report;
	}

	private static void setExportParameter(JRAbstractExporter exporter) {

		// set character encoding
		if (exporter.getClass().isAssignableFrom(JRXlsxExporter.class)) {
			exporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING, "UTF-8");

			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);

			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BACKGROUND, Boolean.FALSE);

			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);

		} else if (exporter.getClass().isAssignableFrom(JRCsvExporter.class)) {
			exporter.setParameter(JRCsvExporterParameter.CHARACTER_ENCODING, "BIG5");
		}
		// throw not-support exception
	}

}
