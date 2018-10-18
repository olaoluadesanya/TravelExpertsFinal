package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bookings database table.
 * Olaoluwa Adesanya SAIT 2018
 */
@Entity
@Table(name="bookings")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bookingDate;

	private String bookingNo;

	private double travelerCount;

	private String tripTypeId;

	//bi-directional many-to-one association to Bookingdetail
	//TRANSIENT PREVENTS PROCESSING OF BOOKING DETAIL IN GSON
	@OneToMany(mappedBy="booking")
	private transient List<Bookingdetail> bookingdetails;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Packag
	@ManyToOne
	@JoinColumn(name="PackageId")
	private Packag packag;

	public Booking() {
	}

	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookingDate() {
		return this.bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingNo() {
		return this.bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public double getTravelerCount() {
		return this.travelerCount;
	}

	public void setTravelerCount(double travelerCount) {
		this.travelerCount = travelerCount;
	}

	public String getTripTypeId() {
		return this.tripTypeId;
	}

	public void setTripTypeId(String tripTypeId) {
		this.tripTypeId = tripTypeId;
	}

	public List<Bookingdetail> getBookingdetails() {
		return this.bookingdetails;
	}

	public void setBookingdetails(List<Bookingdetail> bookingdetails) {
		this.bookingdetails = bookingdetails;
	}

	public Bookingdetail addBookingdetail(Bookingdetail bookingdetail) {
		getBookingdetails().add(bookingdetail);
		bookingdetail.setBooking(this);

		return bookingdetail;
	}

	public Bookingdetail removeBookingdetail(Bookingdetail bookingdetail) {
		getBookingdetails().remove(bookingdetail);
		bookingdetail.setBooking(null);

		return bookingdetail;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Packag getPackag() {
		return this.packag;
	}

	public void setPackag(Packag packag) {
		this.packag = packag;
	}

}