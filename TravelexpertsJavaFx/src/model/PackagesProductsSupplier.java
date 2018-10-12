/*
 * Author: Sunghyun Lee
 * Created: 2018-10-11
 */

package model;


public class PackagesProductsSupplier
{
	
	
	private int packageId;

	private int productSupplierId;

	public PackagesProductsSupplier(int packageId, int productSupplierId)
	{
		super();
		this.packageId = packageId;
		this.productSupplierId = productSupplierId;
	}

	public int getPackageId()
	{
		return packageId;
	}

	public void setPackageId(int packageId)
	{
		this.packageId = packageId;
	}

	public int getProductSupplierId()
	{
		return productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId)
	{
		this.productSupplierId = productSupplierId;
	}
	
	
}
