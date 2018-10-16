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
	private Product product;
	private int supplierId;
	private Supplier supplier;

	public ProductsSupplier(int productSupplierId, int productId, Product product, int supplierId, Supplier supplier)
	{
		super();
		this.productSupplierId = productSupplierId;
		this.productId = productId;
		this.product = product;
		this.supplierId = supplierId;
		this.supplier = supplier;
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
	
	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public int getSupplierId()
	{
		return supplierId;
	}

	public void setSupplierId(int supplierId)
	{
		this.supplierId = supplierId;
	}
	
	public Supplier getSupplier()
	{
		return supplier;
	}

	public void setSupplier(Supplier supplier)
	{
		this.supplier = supplier;
	}
	
}
