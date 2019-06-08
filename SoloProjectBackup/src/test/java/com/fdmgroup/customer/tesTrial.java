/*package com.fdmgroup.customer;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.Customer;
import com.fdmgroup.daoImpl.CustomerDaoImpl;

public class tesTrial {
	
	private Customer tempCustomer1, tempCustomer2, tempCustomer3;
	private CustomerDaoImpl targetCustomerDaoImpl;
	
	
	@Before
	public void setup() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from XCustomer").executeUpdate();
		transaction.commit();

		targetCustomerDaoImpl = new CustomerDaoImpl();
		
		tempCustomer1 = new Customer(1, "Customer1", "Ship Yard, London, L12 3RQ", "cust@hotmail.com", "Ship Yard, London, L12 3RQ");
		tempCustomer2 = new Customer(2, "John", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com", "Birmingham street, Birmingham, B32 9LP");
		tempCustomer3 = new Customer(3, "Potter", "1/4 Street Palace", "harry.magic@gmail.com", "1/4 Street Palace");
		
	}

	@Test
	public void test_listOfCustomersRetrunsEmptyListWhenNoCustomersAdded() {

		assertEquals(0, targetCustomerDaoImpl.listOfCustomers().size());
		//or as under 
		assertTrue(targetCustomerDaoImpl.listOfCustomers().isEmpty());
	}

	@Test
	public void test_listOfCustomersReturnsListWithsizeOneWhenOneCustomerAdded() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		assertEquals(1, targetCustomerDaoImpl.listOfCustomers().size());
	}

	@Test
	public void test_listOfCustomersReturnsListWithSameCustomerAsTheOneAdded() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		assertEquals(tempCustomer1, targetCustomerDaoImpl.listOfCustomers().get(0));
	}
	
	@Test
	public void test_listOfCustomersReturnsListOfSizeTwo_WhenTwoCustomersAdded() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		assertEquals(2, targetCustomerDaoImpl.listOfCustomers().size());
	}
	
	
	@Test
	public void test_getCustomerReturnsACustomerAsPerIndexProvided() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);

		String expected = tempCustomer2.getCustomerName();
		String actual = targetCustomerDaoImpl.getCustomer(1).getCustomerName();
		assertEquals(expected, actual);

	}

	@Test
	public void test_getCustomerReturnsCustomerHavingMatchingAddressAsRequired(){
		
		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		String expected = tempCustomer1.getAddress();
		String actual = targetCustomerDaoImpl.getCustomer(0).getAddress();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_removeCustomerRemovesACustomerFromTheList_AndReducesSizeOfReturnedListOfCustomerByOne() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		int initialSizeOfList = targetCustomerDaoImpl.listOfCustomers().size();
		targetCustomerDaoImpl.removeCustomer(1);
		assertEquals((initialSizeOfList - 1), targetCustomerDaoImpl.listOfCustomers().size());

	}

	@Test
	public void test_ifRemoveCustomerHasCalledTwiceThenTheReturnListOfCustomerSizeWouldDecreseByTwo() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);
		int initialSizeOfList = targetCustomerDaoImpl.listOfCustomers().size();
		targetCustomerDaoImpl.removeCustomer(1);
		targetCustomerDaoImpl.removeCustomer(2);
		assertEquals((initialSizeOfList - 2), targetCustomerDaoImpl.listOfCustomers().size());
	}
	
	@Test
	public void test_updateProductUpdatesTheCustomerNameAsPerNewNameProvided() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);
		
		//Double newPriceProvided = 99.99d;
		String updatedName = "Micky";
	
		tempCustomer1.setCustomerName(updatedName);
		targetCustomerDaoImpl.updateCustomer(tempCustomer1);

		String expected = tempCustomer1.getCustomerName();
		String actual = targetCustomerDaoImpl.getCustomer(0).getCustomerName();

		assertEquals(expected, actual);
	}
	
	@Test
	public void test_updateCustomerUpdatesTheCustomertAddressAsPerNewAddressProvided() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);
		
		String customerNewAddress = "45 House in Brick Lane, Manchester, M35 90L";
		
		tempCustomer3.setAddress(customerNewAddress);
		targetCustomerDaoImpl.updateCustomer(tempCustomer3);


		String expected = tempCustomer3.getAddress();
		String actual = targetCustomerDaoImpl.getCustomer(0).getAddress();

		assertEquals(expected, actual);
	}
	
	@Test
	public void test_updateCustomerUpdatesTheCustomerShippingAddressAsPerNewShippingAddressProvided(){
		
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		
		String newShippingAddress = "This is my Updated Shipping Address";
		tempCustomer2.setAddress(newShippingAddress);
		targetCustomerDaoImpl.updateCustomer(tempCustomer2);
		
		String expected = tempCustomer2.getShipAddress();
		String actual = targetCustomerDaoImpl.getCustomer(0).getShipAddress();
		
		assertEquals(expected, actual);
	}
}
*/