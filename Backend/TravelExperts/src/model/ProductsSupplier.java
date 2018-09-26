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

	//bi-directional many-to-one association to PackagesProductsSupplier
	@OneToMany(mappedBy="productsSupplier")
	private List<PackagesProductsSupplier> packagesProductsSuppliers;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name="SupplierId")
	private Supplier supplier;

	//bi-directional many-to-many association to Packag
	@ManyToMany
	@JoinTable(
		name="packages_products_suppliers"
		, joinColumns={
			@JoinColumn(name="ProductSupplierId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="PackageId")
			}
		)
	private List<Packag> packags;

	public ProductsSupplier() {
	}

	public int getProductSupplierId() {
		return this.productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
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

	public List<Packag> getPackags() {
		return this.packags;
	}

	public void setPackags(List<Packag> packags) {
		this.packags = packags;
	}

}