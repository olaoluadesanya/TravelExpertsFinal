package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products_suppliers database table.
 * 
 */
@Entity
@Table(name="products_suppliers")
@NamedQuery(name="ProductsSupplier.findAll", query="SELECT p FROM ProductsSupplier p")
public class ProductsSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productSupplierId;

	private int supplierId;
	
	private int productId;



	public ProductsSupplier() {
	}

	public int getProductSupplierId() {
		return this.productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


}