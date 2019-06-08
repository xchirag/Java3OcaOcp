package com.fdmgroup.customerDao;

import java.util.List;

public interface CustomerDao {
	
	public void addCustomer (Customer customer); 
	
	public Customer getCustomer (int customerId);
	
	public void removeCustomer (int customerNumber);
	
	public void updateCustomer (Customer customer);
	
	public List<Customer> listOfCustomers();

}
