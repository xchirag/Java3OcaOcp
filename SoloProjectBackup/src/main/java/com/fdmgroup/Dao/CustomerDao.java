package com.fdmgroup.Dao;

import java.util.List;

import com.fdmgroup.entity.Customer;

public interface CustomerDao {
	
	public void addCustomer (Customer customer); 
	
	public Customer getCustomer (int customerId);
	
	public void removeCustomer (int customerNumber);
	
	public void updateCustomer (Customer customer);
	
	public List<Customer> listOfCustomers();

}
