package model;

public class TripType {
	
	private String tripTypeId;
	
	private String ttName;

	public String getTripTypeId() {
		return tripTypeId;
	}

	public void setTripTypeId(String tripTypeId) {
		this.tripTypeId = tripTypeId;
	}

	public String getTtName() {
		return ttName;
	}

	public void setTtName(String ttName) {
		this.ttName = ttName;
	}

	public TripType(String tripTypeId, String ttName) {
		super();
		this.tripTypeId = tripTypeId;
		this.ttName = ttName;
	}

}
