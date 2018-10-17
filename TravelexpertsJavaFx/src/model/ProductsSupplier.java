/*
 * This file has many authors
 * Author: Sunghyun Lee, Corinne Mullan
 * Created: 2018-10-01
 * 
 * Added product and supplier
 * Corinne Mullan
 * 2018-10-15
 */

package model;

public class ProductsSupplier
{
	private int productSupplierId;
	private int productId;		
	private String prodName;
	private int supplierId;
	private String supName;
	
	
	public ProductsSupplier(int productSupplierId, int productId, String prodName, int supplierId, String supName) {
		super();
		this.productSupplierId = productSupplierId;
		this.productId = productId;
		this.prodName = prodName;
		this.supplierId = supplierId;
		this.supName = supName;
	}


	public int getProductSupplierId() {
		return productSupplierId;
	}


	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


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

	
}
	