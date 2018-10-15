/*
 * Author: Corinne Mullan
 * Created: 2018-10-13
 */

package model;

public class Supplier {
	
	// Private member variables
	private int supplierId;
	private String supName;

	
	// Constructor
	public Supplier(int supplierId, String supName)
	{
		super();
		this.supplierId = supplierId;
		this.supName = supName;
	}


	// Getters and Setters
	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}


	// toString method
	@Override
	public String toString()
	{
		return supName;
	}
	
	
}
