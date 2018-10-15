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

	public PackagesProductsSupplier() {
	}

	public PackagesProductsSupplierPK getId() {
		return this.id;
	}

	public void setId(PackagesProductsSupplierPK id) {
		this.id = id;
	}

}