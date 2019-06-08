package com.fdmgroup.customerDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "XCustomer")
public class Customer {

	@Id
	@Column(name="CustomerNumber")
	private int customerNumber;
	
	@Column(name="Customer_Name")
	private String customerName;

	@Column
	private String address;

	@Column
	private String email;

	@Column
	private String shipAddress;

	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", address=" + address + ", email=" + email + ", shipAddress="
				+ shipAddress + ", customerNumber=" + customerNumber + "]";
	}

	public Customer(int customerNumber, String customerName, String address, String email, String shipAddress) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.address = address;
		this.email = email;
		this.shipAddress = shipAddress;
	}

	public Customer(){
		
		//empty constructor require for JPA setup
	}
}
