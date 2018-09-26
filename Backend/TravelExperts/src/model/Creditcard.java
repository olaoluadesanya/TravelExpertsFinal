package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the creditcards database table.
 * 
 */
@Entity
@Table(name="creditcards")
@NamedQuery(name="Creditcard.findAll", query="SELECT c FROM Creditcard c")
public class Creditcard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int creditCardId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date CCExpiry;

	private String CCName;

	private String CCNumber;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Creditcard() {
	}

	public int getCreditCardId() {
		return this.creditCardId;
	}

	public void setCreditCardId(int creditCardId) {
		this.creditCardId = creditCardId;
	}

	public Date getCCExpiry() {
		return this.CCExpiry;
	}

	public void setCCExpiry(Date CCExpiry) {
		this.CCExpiry = CCExpiry;
	}

	public String getCCName() {
		return this.CCName;
	}

	public void setCCName(String CCName) {
		this.CCName = CCName;
	}

	public String getCCNumber() {
		return this.CCNumber;
	}

	public void setCCNumber(String CCNumber) {
		this.CCNumber = CCNumber;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}