package example;

public class AdditionalBean {

	private String innerName;
	private Integer innerValue;

	public AdditionalBean() {

	}

	public AdditionalBean(String innerName, Integer innerValue) {
		this.innerName = innerName;
		this.innerValue = innerValue;
	}

	public String getInnerName() {
		return innerName;
	}

	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}

	public Integer getInnerValue() {
		return innerValue;
	}

	public void setInnerValue(Integer innerValue) {
		this.innerValue = innerValue;
	}
}
