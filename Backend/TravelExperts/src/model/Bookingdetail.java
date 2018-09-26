package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the bookingdetails database table.
 * 
 */
@Entity
@Table(name="bookingdetails")
@NamedQuery(name="Bookingdetail.findAll", query="SELECT b FROM Bookingdetail b")
public class Bookingdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int bookingDetailId;

	private BigDecimal agencyCommission;

	private BigDecimal basePrice;

	private Object description;

	private Object destination;

	private double itineraryNo;

	private int productSupplierId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tripEnd;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tripStart;

	//bi-directional many-to-one association to Booking
	@ManyToOne
	@JoinColumn(name="BookingId")
	private Booking booking;

	//bi-directional many-to-one association to Fee
	@ManyToOne
	@JoinColumn(name="FeeId")
	private Fee fee;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="RegionId")
	private Region region;

	//bi-directional many-to-one association to Clas
	@ManyToOne
	@JoinColumn(name="ClassId")
	private Clas clas;

	public Bookingdetail() {
	}

	public int getBookingDetailId() {
		return this.bookingDetailId;
	}

	public void setBookingDetailId(int bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}

	public BigDecimal getAgencyCommission() {
		return this.agencyCommission;
	}

	public void setAgencyCommission(BigDecimal agencyCommission) {
		this.agencyCommission = agencyCommission;
	}

	public BigDecimal getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Object getDescription() {
		return this.description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public Object getDestination() {
		return this.destination;
	}

	public void setDestination(Object destination) {
		this.destination = destination;
	}

	public double getItineraryNo() {
		return this.itineraryNo;
	}

	public void setItineraryNo(double itineraryNo) {
		this.itineraryNo = itineraryNo;
	}

	public int getProductSupplierId() {
		return this.productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public Date getTripEnd() {
		return this.tripEnd;
	}

	public void setTripEnd(Date tripEnd) {
		this.tripEnd = tripEnd;
	}

	public Date getTripStart() {
		return this.tripStart;
	}

	public void setTripStart(Date tripStart) {
		this.tripStart = tripStart;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Fee getFee() {
		return this.fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Clas getClas() {
		return this.clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}

}