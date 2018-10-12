/*
 * Author: Sunghyun Lee
 * Created: 2018-10-01
 */
package model;

public class Product
{
	private int productId;

	private String prodName;

	public Product(int productId, String prodName)
	{
		super();
		this.productId = productId;
		this.prodName = prodName;
	}

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	public String getProdName()
	{
		return prodName;
	}

	public void setProdName(String prodName)
	{
		this.prodName = prodName;
	}

	@Override
	public String toString()
	{
		return prodName;
	}
	
	
}
