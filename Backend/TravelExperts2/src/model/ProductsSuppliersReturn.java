/*
 * Ported into web service from JavaFX project
 * 
 * Author: Sunghyun Lee
 * Created: 2018-10-01
 * 
 * Added prodName and supName
 * Corinne Mullan
 * 2018-10-15
 */

package model;

import java.io.Serializable;

public class ProductsSuppliersReturn implements Serializable
{
	private int productSupplierId;
	private int productId;		
	private String prodName;
	private int supplierId;
	private String supName;

	public ProductsSuppliersReturn(int productSupplierId, int productId, String prodName, int supplierId, String supName)
	{
		this.productSupplierId = productSupplierId;
		this.productId = productId;
		this.prodName = prodName;
		this.supplierId = supplierId;
		this.supName = supName;
	}
	
	public int getProductSupplierId()
	{
		return productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId)
	{
		this.productSupplierId = productSupplierId;
	}
	
	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productSupplierId)
	{
		this.productId = productSupplierId;
	}
	
	public String getProdName()
	{
		return prodName;
	}

	public void setProdName(String prodName)
	{
		this.prodName = prodName;
	}

	public int getSupplierId()
	{
		return supplierId;
	}

	public void setSupplierId(int supplierId)
	{
		this.supplierId = supplierId;
	}
	
	public String getSupName()
	{
		return supName;
	}

	public void setSupName(String supName)
	{
		this.supName = supName;
	}

	@Override
	public String toString() {
		return "{\"productSupplierId\":" + productSupplierId + ", \"productId\"=" + productId
				+ ", \"prodName\"=\"" + prodName + "\", \"supplierId\"=" + supplierId + ", \"supName\"=\"" + supName 
				+ "\"}";
	}
	
	
	
}
