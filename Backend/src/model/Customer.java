package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int customerId;

	private String custAddress;

	private String custBusPhone;

	private String custCity;

	private String custCountry;

	private String custEmail;

	private String custFirstName;

	private String custHomePhone;

	private String custLastName;

	private String custPostal;

	private String custProv;

	private String passwd;

	private String userid;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="customer")
	private transient List<Booking> bookings;

	//bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name="AgentId")
	private Agent agent;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustBusPhone() {
		return this.custBusPhone;
	}

	public void setCustBusPhone(String custBusPhone) {
		this.custBusPhone = custBusPhone;
	}

	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustCountry() {
		return this.custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustFirstName() {
		return this.custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustHomePhone() {
		return this.custHomePhone;
	}

	public void setCustHomePhone(String custHomePhone) {
		this.custHomePhone = custHomePhone;
	}

	public String getCustLastName() {
		return this.custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCustPostal() {
		return this.custPostal;
	}

	public void setCustPostal(String custPostal) {
		this.custPostal = custPostal;
	}

	public String getCustProv() {
		return this.custProv;
	}

	public void setCustProv(String custProv) {
		this.custProv = custProv;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setCustomer(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setCustomer(null);

		return booking;
	}

	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}