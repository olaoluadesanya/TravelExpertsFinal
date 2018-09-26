package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rewards database table.
 * 
 */
@Entity
@Table(name="rewards")
@NamedQuery(name="Reward.findAll", query="SELECT r FROM Reward r")
public class Reward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int rewardId;

	private String rwdDesc;

	private String rwdName;

	//bi-directional many-to-one association to CustomersReward
	@OneToMany(mappedBy="reward")
	private List<CustomersReward> customersRewards;

	public Reward() {
	}

	public int getRewardId() {
		return this.rewardId;
	}

	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public String getRwdDesc() {
		return this.rwdDesc;
	}

	public void setRwdDesc(String rwdDesc) {
		this.rwdDesc = rwdDesc;
	}

	public String getRwdName() {
		return this.rwdName;
	}

	public void setRwdName(String rwdName) {
		this.rwdName = rwdName;
	}

	public List<CustomersReward> getCustomersRewards() {
		return this.customersRewards;
	}

	public void setCustomersRewards(List<CustomersReward> customersRewards) {
		this.customersRewards = customersRewards;
	}

	public CustomersReward addCustomersReward(CustomersReward customersReward) {
		getCustomersRewards().add(customersReward);
		customersReward.setReward(this);

		return customersReward;
	}

	public CustomersReward removeCustomersReward(CustomersReward customersReward) {
		getCustomersRewards().remove(customersReward);
		customersReward.setReward(null);

		return customersReward;
	}

}