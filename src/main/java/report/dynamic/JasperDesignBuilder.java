package report.dynamic;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import report.dynamic.element.JSElement;

public class JasperDesignBuilder {

	private static final int CELL_WIDTH = 100;
	private static final int CELL_HEIGHT = 60;
	private JasperDesign jasperDesign;

	public JasperDesignBuilder(InputStream in) throws JRException, IOException {
		try (InputStream is = in;) {
			this.jasperDesign = JRXmlLoader.load(is);
		}
	}

	public JasperDesignBuilder(String jrxmlName) throws JRException, IOException {
		this(JasperDesignBuilder.class.getClassLoader().getResource("jrxml/" + jrxmlName).openStream());
	}

	public JasperDesign build(String name) {
		jasperDesign.setName(name);
		return this.jasperDesign;
	}

	public JasperDesignBuilder setPageHeader(List<List<JSElement>> rows, DynamicElementInfo info) throws JRException {
		JRDesignBand band = createJSBand(rows, info);
		this.jasperDesign.setPageHeader(band);
		return this;
	}

	public JasperDesignBuilder setColumnHeader(List<List<JSElement>> rows, DynamicElementInfo info) throws JRException {

		JRDesignBand band = createJSBand(rows, info);
		this.jasperDesign.setColumnHeader(band);
		return this;
	}

	public JasperDesignBuilder setDetail(List<List<JSElement>> rows, DynamicElementInfo info) throws JRException {
		JRDesignBand band = createJSBand(rows, info);
		((JRDesignSection) this.jasperDesign.getDetailSection()).addBand(band);
		return this;
	}

	private JRDesignBand createJSBand(List<List<JSElement>> rows, DynamicElementInfo info)
			throws JRException {

		JRDesignBand band = createJRDesignBand(rows.size());
		int yPos = 0;
		for (List<JSElement> row : rows) {
			List<JSElement> cols = JSDynamicHandler.insert(row, info);
			int xPos = 0;
			for (JSElement col : cols) {
				JRDesignTextField element = createTextField(col.getJSText(), col.getType(), xPos, yPos,
						col.getBackcolor(), col.getForecolor());
				band.addElement(element);
				if (col.isParameter()) {
					createJSParameter(col.getText(), col.getType());
				} else if (col.isField()) {
					createJSField(col.getText(), col.getType());
				}
				xPos += CELL_WIDTH;
			}
			yPos += CELL_HEIGHT;
		}
		return band;
	}

	private JRDesignBand createJRDesignBand(int count) {
		JRDesignBand band = new JRDesignBand();
		band.setHeight(count * CELL_HEIGHT);
		band.setSplitType(SplitTypeEnum.STRETCH);
		return band;
	}

	private void createJSField(String name, Class<?> type) throws JRException {
		JRDesignField designField = new JRDesignField();
		designField.setName(name);
		designField.setValueClass(type);
		this.jasperDesign.addField(designField);
	}

	public JasperDesignBuilder createJSParameters(Map<String, Class<?>> map) throws JRException {
		for (Map.Entry<String, Class<?>> entry : map.entrySet()) {
			createJSParameter(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public JasperDesignBuilder createJSParameter(String name, Class<?> type) throws JRException {
		JRDesignParameter parameter = new JRDesignParameter();
		parameter.setName(name);
		parameter.setValueClass(type);
		JRDesignExpression expression = new JRDesignExpression();
		expression.setText("");
		parameter.setDefaultValueExpression(expression);
		this.jasperDesign.addParameter(parameter);
		return this;
	}

	private JRDesignTextField createTextField(String name, Class<?> type, int xPos, int yPos, Color backcolor,
			Color forecolor) throws JRException {

		JRDesignExpression expression = new JRDesignExpression();
		expression.setValueClass(type);
		expression.setText(name);

		JRDesignTextField textField = new JRDesignTextField();
		textField.setX(xPos);
		textField.setY(yPos);
		textField.setWidth(CELL_WIDTH);
		textField.setHeight(CELL_HEIGHT);
		textField.setMode(ModeEnum.OPAQUE);

		textField.setBackcolor(backcolor);
		textField.setForecolor(forecolor);
		textField.setFontSize(12);

		textField.getLineBox().getPen().setLineWidth(0.5f);
		textField.getLineBox().getPen().setLineColor(Color.BLACK);
		textField.getLineBox().getPen().setLineStyle(LineStyleEnum.SOLID);

		textField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
		textField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		textField.setExpression(expression);

		return textField;
	}

}
