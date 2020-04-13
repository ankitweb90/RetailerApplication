package com.retailer.attributes;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SummaryTransaction<K, V> {
	
	private String customerName;
	private Map<K, V> rewardsPerMonth;
	private int totalRewards;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Map<K, V> getRewardsPerMonth() {
		return rewardsPerMonth;
	}
	public void setRewardsPerMonth(Map<K, V> rewardsPerMonth) {
		this.rewardsPerMonth = rewardsPerMonth;
	}
	public int getTotalRewards() {
		return totalRewards;
	}
	public void setTotalRewards(int totalRewards) {
		this.totalRewards = totalRewards;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((rewardsPerMonth == null) ? 0 : rewardsPerMonth.hashCode());
		result = prime * result + totalRewards;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SummaryTransaction other = (SummaryTransaction) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (rewardsPerMonth == null) {
			if (other.rewardsPerMonth != null)
				return false;
		} else if (!rewardsPerMonth.equals(other.rewardsPerMonth))
			return false;
		if (totalRewards != other.totalRewards)
			return false;
		return true;
	}
	
	

	

}
