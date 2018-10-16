package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the suppliers database table.
 * 
 */
@Entity
@Table(name="suppliers")
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int supplierId;

	private Object supName;

	//bi-directional many-to-one association to ProductsSupplier
	@OneToMany(mappedBy="supplier")
	private transient List<ProductsSupplier> productsSuppliers;

	public Supplier() {
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public Object getSupName() {
		return this.supName;
	}

	public void setSupName(Object supName) {
		this.supName = supName;
	}

	public List<ProductsSupplier> getProductsSuppliers() {
		return this.productsSuppliers;
	}

	public void setProductsSuppliers(List<ProductsSupplier> productsSuppliers) {
		this.productsSuppliers = productsSuppliers;
	}

	public ProductsSupplier addProductsSupplier(ProductsSupplier productsSupplier) {
		getProductsSuppliers().add(productsSupplier);
		productsSupplier.setSupplier(this);

		return productsSupplier;
	}

	public ProductsSupplier removeProductsSupplier(ProductsSupplier productsSupplier) {
		getProductsSuppliers().remove(productsSupplier);
		productsSupplier.setSupplier(null);

		return productsSupplier;
	}

}