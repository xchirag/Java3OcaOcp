package com.fdmgroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//import com.fdmgroup.customerDao.Customer;

@Entity(name="ng_bankaccount")
public class BankAccount {

	
	@ManyToOne
	@JoinColumn(name="CustomerNumber")
	private Customer customer;
	
		
	@Column
	private Double accountBalance;
	
	@Id
	private int bankId;
	
	
	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public BankAccount(){
		
	}
	
	@Override
	public String toString() {
		return "BankAccount [customer=" + customer + ", accountBalance=" + accountBalance + "]";
	}



	public BankAccount(int bankId, Customer customer, Double accountBalance) {
		super();
		this.bankId = bankId;
		this.customer = customer;
		this.accountBalance = accountBalance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	
	public void addBalance(int customerNumber, Double depositAmount) {

	}

	public void withdrawBalance(int customerNumber, Double depositAmount) {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + bankId;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		BankAccount other = (BankAccount) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (bankId != other.bankId)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	
}
