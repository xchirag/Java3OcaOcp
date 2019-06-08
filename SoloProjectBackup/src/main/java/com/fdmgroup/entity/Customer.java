package com.fdmgroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ng_customer")
public class Customer {

	@Id
	@Column(name = "CustomerNumber")
	private int customerNumber;

	@Column(name = "Customer_Name")
	private String customerName;

	@Column(name = "password") // this is added in 6th week!
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	private String address;

	@Column
	private String email;

	@Column
	private String shipAddress;
	
	@Column
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public Customer() {

		// empty constructor require for JPA setup
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + customerNumber;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((shipAddress == null) ? 0 : shipAddress.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerNumber != other.customerNumber)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (shipAddress == null) {
			if (other.shipAddress != null)
				return false;
		} else if (!shipAddress.equals(other.shipAddress))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public Customer(int customerNumber, String customerName, String password, String address, String email,
			String shipAddress, String userName) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.shipAddress = shipAddress;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", password="
				+ password + ", address=" + address + ", email=" + email + ", shipAddress=" + shipAddress
				+ ", userName=" + userName + "]";
	}

	

}
