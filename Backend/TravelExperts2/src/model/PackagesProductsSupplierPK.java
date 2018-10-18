package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the packages_products_suppliers database table.
 * Olaoluwa Adesanya SAIT 2018
 */
@Embeddable
public class PackagesProductsSupplierPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "PackageId")
	private int packageId;

	@Column(name = "ProductSupplierId")
	private int productSupplierId;

	public PackagesProductsSupplierPK() {
	}
	public int getPackageId() {
		return this.packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getProductSupplierId() {
		return this.productSupplierId;
	}
	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PackagesProductsSupplierPK)) {
			return false;
		}
		PackagesProductsSupplierPK castOther = (PackagesProductsSupplierPK)other;
		return 
			(this.packageId == castOther.packageId)
			&& (this.productSupplierId == castOther.productSupplierId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.packageId;
		hash = hash * prime + this.productSupplierId;
		
		return hash;
	}
}