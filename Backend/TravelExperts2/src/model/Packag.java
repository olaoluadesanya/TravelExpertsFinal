package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the packags database table.
 * Olaoluwa Adesanya SAIT 2018
 * had to change name from package to packag as package is ajava reserved keyword
 */
@Entity
@Table(name="packags")
@NamedQuery(name="Packag.findAll", query="SELECT p FROM Packag p")
public class Packag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int packageId;

	private BigDecimal pkgAgencyCommission;

	private BigDecimal pkgBasePrice;

	private String pkgDesc;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pkgEndDate;

	private String pkgName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pkgStartDate;
	
	private String pkgImageFile;
	

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="packag")
	private transient List<Booking> bookings;

	public Packag() {
	}

	public int getPackageId() {
		return this.packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public BigDecimal getPkgAgencyCommission() {
		return this.pkgAgencyCommission;
	}

	public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	public BigDecimal getPkgBasePrice() {
		return this.pkgBasePrice;
	}

	public void setPkgBasePrice(BigDecimal pkgBasePrice) {
		this.pkgBasePrice = pkgBasePrice;
	}

	public String getPkgDesc() {
		return this.pkgDesc;
	}

	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}

	public Date getPkgEndDate() {
		return this.pkgEndDate;
	}

	public void setPkgEndDate(Date pkgEndDate) {
		this.pkgEndDate = pkgEndDate;
	}

	public String getPkgName() {
		return this.pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public Date getPkgStartDate() {
		return this.pkgStartDate;
	}

	public void setPkgStartDate(Date pkgStartDate) {
		this.pkgStartDate = pkgStartDate;
	}
	
	public String getPkgImageFile() {
		return this.pkgImageFile;
	}

	public void setPkgImageFile(String pkgImageFile) {
		this.pkgImageFile = pkgImageFile;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setPackag(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setPackag(null);

		return booking;
	}

}