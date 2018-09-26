package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the affiliations database table.
 * 
 */
@Entity
@Table(name="affiliations")
@NamedQuery(name="Affiliation.findAll", query="SELECT a FROM Affiliation a")
public class Affiliation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String affilitationId;

	private String affDesc;

	private String affName;

	//bi-directional many-to-one association to Suppliercontact
	@OneToMany(mappedBy="affiliation")
	private List<Suppliercontact> suppliercontacts;

	public Affiliation() {
	}

	public String getAffilitationId() {
		return this.affilitationId;
	}

	public void setAffilitationId(String affilitationId) {
		this.affilitationId = affilitationId;
	}

	public String getAffDesc() {
		return this.affDesc;
	}

	public void setAffDesc(String affDesc) {
		this.affDesc = affDesc;
	}

	public String getAffName() {
		return this.affName;
	}

	public void setAffName(String affName) {
		this.affName = affName;
	}

	public List<Suppliercontact> getSuppliercontacts() {
		return this.suppliercontacts;
	}

	public void setSuppliercontacts(List<Suppliercontact> suppliercontacts) {
		this.suppliercontacts = suppliercontacts;
	}

	public Suppliercontact addSuppliercontact(Suppliercontact suppliercontact) {
		getSuppliercontacts().add(suppliercontact);
		suppliercontact.setAffiliation(this);

		return suppliercontact;
	}

	public Suppliercontact removeSuppliercontact(Suppliercontact suppliercontact) {
		getSuppliercontacts().remove(suppliercontact);
		suppliercontact.setAffiliation(null);

		return suppliercontact;
	}

}