package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="triptype")
	private List<Booking> bookings;

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

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setTriptype(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setTriptype(null);

		return booking;
	}

}