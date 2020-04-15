package com.retailer.attributes;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	
	private String customerName;
	private BigDecimal transactionAmount;
	private Date transactionDate;
	
	public Transaction(String customerName, BigDecimal transactionAmount, Date date) {
		super();
		this.customerName = customerName;
		this.transactionAmount = transactionAmount;
		this.transactionDate = date;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}

