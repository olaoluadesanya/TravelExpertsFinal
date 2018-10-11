package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the agents database table.
 * 
 */
@Entity
@Table(name="agents")
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int agentId;

	private int agencyId;

	private String agtBusPhone;

	private String agtEmail;

	private String agtFirstName;

	private String agtLastName;

	private String agtMiddleInitial;

	private String agtPosition;
	
	private String Pass;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="agent")
	private transient List<Customer> customers;

	public Agent() {
	}

	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getAgencyId() {
		return this.agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgtBusPhone() {
		return this.agtBusPhone;
	}

	public void setAgtBusPhone(String agtBusPhone) {
		this.agtBusPhone = agtBusPhone;
	}

	public String getAgtEmail() {
		return this.agtEmail;
	}

	public void setAgtEmail(String agtEmail) {
		this.agtEmail = agtEmail;
	}

	public String getAgtFirstName() {
		return this.agtFirstName;
	}

	public void setAgtFirstName(String agtFirstName) {
		this.agtFirstName = agtFirstName;
	}

	public String getAgtLastName() {
		return this.agtLastName;
	}

	public void setAgtLastName(String agtLastName) {
		this.agtLastName = agtLastName;
	}

	public String getAgtMiddleInitial() {
		return this.agtMiddleInitial;
	}

	public void setAgtMiddleInitial(String agtMiddleInitial) {
		this.agtMiddleInitial = agtMiddleInitial;
	}

	public String getAgtPosition() {
		return this.agtPosition;
	}

	public void setAgtPosition(String agtPosition) {
		this.agtPosition = agtPosition;
	}
	
	public String getPass() {
		return this.Pass;
	}

	public void setPass(String Pass) {
		this.Pass = Pass;
	}
	
	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setAgent(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setAgent(null);

		return customer;
	}

}