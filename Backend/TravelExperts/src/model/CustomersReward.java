package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customers_rewards database table.
 * 
 */
@Entity
@Table(name="customers_rewards")
@NamedQuery(name="CustomersReward.findAll", query="SELECT c FROM CustomersReward c")
public class CustomersReward implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CustomersRewardPK id;

	private String rwdNumber;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Reward
	@ManyToOne
	@JoinColumn(name="RewardId")
	private Reward reward;

	public CustomersReward() {
	}

	public CustomersRewardPK getId() {
		return this.id;
	}

	public void setId(CustomersRewardPK id) {
		this.id = id;
	}

	public String getRwdNumber() {
		return this.rwdNumber;
	}

	public void setRwdNumber(String rwdNumber) {
		this.rwdNumber = rwdNumber;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Reward getReward() {
		return this.reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

}