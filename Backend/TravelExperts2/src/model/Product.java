package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * Olaoluwa Adesanya SAIT 2018
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productId;

	private String prodName;

	//bi-directional many-to-one association to ProductsSupplier
	@OneToMany(mappedBy="product")
	private transient List<ProductsSupplier> productsSuppliers;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public List<ProductsSupplier> getProductsSuppliers() {
		return this.productsSuppliers;
	}

	public void setProductsSuppliers(List<ProductsSupplier> productsSuppliers) {
		this.productsSuppliers = productsSuppliers;
	}

	public ProductsSupplier addProductsSupplier(ProductsSupplier productsSupplier) {
		getProductsSuppliers().add(productsSupplier);
		productsSupplier.setProduct(this);

		return productsSupplier;
	}

	public ProductsSupplier removeProductsSupplier(ProductsSupplier productsSupplier) {
		getProductsSuppliers().remove(productsSupplier);
		productsSupplier.setProduct(null);

		return productsSupplier;
	}

}