package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_suppliers database table.
 * Olaoluwa Adesanya SAIT 2018
 */
@Entity
@Table(name="products_suppliers")
@NamedQuery(name="ProductsSupplier.findAll", query="SELECT p FROM ProductsSupplier p")
public class ProductsSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productSupplierId;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name="SupplierId")
	private Supplier supplier;

	public ProductsSupplier() {
	}

	public int getProductSupplierId() {
		return this.productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}