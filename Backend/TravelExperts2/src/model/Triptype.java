package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the triptypes database table.
 * 
 */
@Entity
@Table(name="triptypes")
@NamedQuery(name="Triptype.findAll", query="SELECT t FROM Triptype t")
public class Triptype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tripTypeId;

	private String TTName;

	public Triptype() {
	}

	public String getTripTypeId() {
		return this.tripTypeId;
	}

	public void setTripTypeId(String tripTypeId) {
		this.tripTypeId = tripTypeId;
	}

	public String getTTName() {
		return this.TTName;
	}

	public void setTTName(String TTName) {
		this.TTName = TTName;
	}

}