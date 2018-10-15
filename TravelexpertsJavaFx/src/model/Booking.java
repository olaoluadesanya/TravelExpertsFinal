package model;

public class Booking {	
	
	private int customerId;
	
	private String classId;
	
	private int packageId;
	
	private String tripTypeId;
	
	private int travelerCount;
	
	private String feeId;
	
	private String destination;
	
	private String description;	
	
	public Booking(int customerId, String classId, int packageId, String tripTypeId, int travelerCount,
			String feeId, String destination, String description) {
		super();	
		this.customerId = customerId;
		this.classId = classId;
		this.packageId = packageId;
		this.tripTypeId = tripTypeId;
		this.travelerCount = travelerCount;
		this.feeId = feeId;
		this.destination = destination;
		this.description = description;
	}
	

	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getTripTypeId() {
		return tripTypeId;
	}

	public void setTripTypeId(String tripTypeId) {
		this.tripTypeId = tripTypeId;
	}

	public int getTravelerCount() {
		return travelerCount;
	}

	public void setTravelerCount(int travelerCount) {
		this.travelerCount = travelerCount;
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
