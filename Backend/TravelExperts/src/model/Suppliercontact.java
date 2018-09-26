package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the suppliercontacts database table.
 * 
 */
@Entity
@Table(name="suppliercontacts")
@NamedQuery(name="Suppliercontact.findAll", query="SELECT s FROM Suppliercontact s")
public class Suppliercontact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int supplierContactId;

	private Object supConAddress;

	private String supConBusPhone;

	private Object supConCity;

	private Object supConCompany;

	private Object supConCountry;

	private Object supConEmail;

	private String supConFax;

	private String supConFirstName;

	private String supConLastName;

	private Object supConPostal;

	private Object supConProv;

	private Object supConURL;

	//bi-directional many-to-one association to Affiliation
	@ManyToOne
	@JoinColumn(name="AffiliationId")
	private Affiliation affiliation;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name="SupplierId")
	private Supplier supplier;

	public Suppliercontact() {
	}

	public int getSupplierContactId() {
		return this.supplierContactId;
	}

	public void setSupplierContactId(int supplierContactId) {
		this.supplierContactId = supplierContactId;
	}

	public Object getSupConAddress() {
		return this.supConAddress;
	}

	public void setSupConAddress(Object supConAddress) {
		this.supConAddress = supConAddress;
	}

	public String getSupConBusPhone() {
		return this.supConBusPhone;
	}

	public void setSupConBusPhone(String supConBusPhone) {
		this.supConBusPhone = supConBusPhone;
	}

	public Object getSupConCity() {
		return this.supConCity;
	}

	public void setSupConCity(Object supConCity) {
		this.supConCity = supConCity;
	}

	public Object getSupConCompany() {
		return this.supConCompany;
	}

	public void setSupConCompany(Object supConCompany) {
		this.supConCompany = supConCompany;
	}

	public Object getSupConCountry() {
		return this.supConCountry;
	}

	public void setSupConCountry(Object supConCountry) {
		this.supConCountry = supConCountry;
	}

	public Object getSupConEmail() {
		return this.supConEmail;
	}

	public void setSupConEmail(Object supConEmail) {
		this.supConEmail = supConEmail;
	}

	public String getSupConFax() {
		return this.supConFax;
	}

	public void setSupConFax(String supConFax) {
		this.supConFax = supConFax;
	}

	public String getSupConFirstName() {
		return this.supConFirstName;
	}

	public void setSupConFirstName(String supConFirstName) {
		this.supConFirstName = supConFirstName;
	}

	public String getSupConLastName() {
		return this.supConLastName;
	}

	public void setSupConLastName(String supConLastName) {
		this.supConLastName = supConLastName;
	}

	public Object getSupConPostal() {
		return this.supConPostal;
	}

	public void setSupConPostal(Object supConPostal) {
		this.supConPostal = supConPostal;
	}

	public Object getSupConProv() {
		return this.supConProv;
	}

	public void setSupConProv(Object supConProv) {
		this.supConProv = supConProv;
	}

	public Object getSupConURL() {
		return this.supConURL;
	}

	public void setSupConURL(Object supConURL) {
		this.supConURL = supConURL;
	}

	public Affiliation getAffiliation() {
		return this.affiliation;
	}

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}