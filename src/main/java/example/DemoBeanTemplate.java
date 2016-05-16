package example;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import report.dynamic.element.JSColor;
import report.dynamic.element.JSDynamic;
import report.dynamic.element.JSElement;
import report.dynamic.element.JSElementType;
import report.dynamic.element.JSField;
import report.dynamic.element.JSFunction;
import report.dynamic.element.JSLabel;
import report.dynamic.element.JSParameter;

public class DemoBeanTemplate {

	public static List<List<JSElement>> pageHeader = new ArrayList<List<JSElement>>();
	static {

		JSColor color = new JSColor(Color.BLACK, Color.WHITE);
		List<JSElement> row1 = new ArrayList<JSElement>();
		row1.add(new JSLabel(color));
		row1.add(new JSDynamic(color, JSElementType.LABEL));
		row1.add(new JSLabel("parameter", color));

		List<JSElement> row2 = new ArrayList<JSElement>();
		row2.add(new JSLabel(color));
		row2.add(new JSDynamic(color, JSElementType.PARAMETER));
		row2.add(new JSParameter("parameter", String.class, color));

		pageHeader.add(row1);
		pageHeader.add(row2);
	}

	public static List<List<JSElement>> columnHeader = new ArrayList<List<JSElement>>();
	static {

		JSColor color = new JSColor(Color.BLACK, Color.WHITE);
		List<JSElement> row = new ArrayList<JSElement>();
		row.add(new JSLabel("NAME", color));
		row.add(new JSDynamic(color, JSElementType.LABEL));
		row.add(new JSLabel("VALUE", color));
		row.add(new JSLabel("OTHER", color));
		columnHeader.add(row);
	}

	public static List<List<JSElement>> detail = new ArrayList<List<JSElement>>();
	static {

		JSColor color = new JSColor(Color.WHITE, Color.BLACK);
		List<JSElement> row1 = new ArrayList<JSElement>();

		row1.add(new JSField("name", color));
		row1.add(new JSDynamic(color, JSElementType.FIELD));
		row1.add(new JSField("value", color));
		row1.add(new JSField("other", color));
		row1.add(new JSFunction("$P{parameter} +\", \" +  $F{name}", color));

		detail.add(row1);
	}

}
