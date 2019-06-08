package com.fdmgroup.classimp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fdmgroup.customerDao.Customer;

@Entity(name="XbankAccount")
public class BankAccount {

	
	@ManyToOne
	@JoinColumn(name="CustomerNumber")
	private Customer customer;
	
		
	@Column
	private Double accountBalance;
	
	@Id
	private int bankId;
	
	
	public BankAccount(){
		
	}
	
	@Override
	public String toString() {
		return "BankAccount [customer=" + customer + ", accountBalance=" + accountBalance + "]";
	}



	public BankAccount(Customer customer, Double accountBalance) {
		super();
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

}
