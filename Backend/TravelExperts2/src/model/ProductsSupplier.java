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

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to PackagesProductsSupplier
	@OneToMany(mappedBy="productsSupplier")
	private transient List<PackagesProductsSupplier> packagesProductsSuppliers;

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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<PackagesProductsSupplier> getPackagesProductsSuppliers() {
		return this.packagesProductsSuppliers;
	}

	public void setPackagesProductsSuppliers(List<PackagesProductsSupplier> packagesProductsSuppliers) {
		this.packagesProductsSuppliers = packagesProductsSuppliers;
	}

	public PackagesProductsSupplier addPackagesProductsSupplier(PackagesProductsSupplier packagesProductsSupplier) {
		getPackagesProductsSuppliers().add(packagesProductsSupplier);
		packagesProductsSupplier.setProductsSupplier(this);

		return packagesProductsSupplier;
	}

	public PackagesProductsSupplier removePackagesProductsSupplier(PackagesProductsSupplier packagesProductsSupplier) {
		getPackagesProductsSuppliers().remove(packagesProductsSupplier);
		packagesProductsSupplier.setProductsSupplier(null);

		return packagesProductsSupplier;
	}

}