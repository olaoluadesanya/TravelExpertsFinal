package model;

public class FeeType {
	
	private String feeId;
	
	private String feeName;

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public FeeType(String feeId, String feeName) {
		super();
		this.feeId = feeId;
		this.feeName = feeName;
	}
	
}
