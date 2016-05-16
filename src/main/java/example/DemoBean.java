package example;

import java.util.List;

public class DemoBean {

	private String name;
	private String value;
	private List<AdditionalBean> additionalInfo;

	public DemoBean(String name, String value, List<AdditionalBean> additionalInfo) {
		this.name = name;
		this.value = value;
		this.additionalInfo = additionalInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<AdditionalBean> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(List<AdditionalBean> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getOther() {
		return getName() + " : " + getValue();
	}

}
