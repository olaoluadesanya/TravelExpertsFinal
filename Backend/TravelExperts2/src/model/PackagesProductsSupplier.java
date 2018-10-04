package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the packages_products_suppliers database table.
 * 
 */
@Entity
@Table(name="packages_products_suppliers")
@NamedQuery(name="PackagesProductsSupplier.findAll", query="SELECT p FROM PackagesProductsSupplier p")
public class PackagesProductsSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PackagesProductsSupplierPK id;

	//bi-directional many-to-one association to Packag
	@ManyToOne
	@JoinColumn(name="PackageId")
	private Packag packag;

	//bi-directional many-to-one association to ProductsSupplier
	@ManyToOne
	@JoinColumn(name="ProductSupplierId")
	private ProductsSupplier productsSupplier;

	public PackagesProductsSupplier() {
	}

	public PackagesProductsSupplierPK getId() {
		return this.id;
	}

	public void setId(PackagesProductsSupplierPK id) {
		this.id = id;
	}

	public Packag getPackag() {
		return this.packag;
	}

	public void setPackag(Packag packag) {
		this.packag = packag;
	}

	public ProductsSupplier getProductsSupplier() {
		return this.productsSupplier;
	}

	public void setProductsSupplier(ProductsSupplier productsSupplier) {
		this.productsSupplier = productsSupplier;
	}

}