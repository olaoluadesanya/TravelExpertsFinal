package model;

public class Customer {
	
	private int CustomerId;

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public Customer(int customerId) {
		super();
		CustomerId = customerId;
	}
	
}
