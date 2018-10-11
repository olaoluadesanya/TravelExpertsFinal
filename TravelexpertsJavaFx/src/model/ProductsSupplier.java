/*
 * Author: Sunghyun Lee
 * Created: 2018-10-01
 */
package model;

public class ProductsSupplier
{
	private int productSupplierId;

	private int supplierId;

	public ProductsSupplier(int productSupplierId, int supplierId)
	{
		super();
		this.productSupplierId = productSupplierId;
		this.supplierId = supplierId;
	}

	public int getProductSupplierId()
	{
		return productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId)
	{
		this.productSupplierId = productSupplierId;
	}

	public int getSupplierId()
	{
		return supplierId;
	}

	public void setSupplierId(int supplierId)
	{
		this.supplierId = supplierId;
	}
	
	
}
