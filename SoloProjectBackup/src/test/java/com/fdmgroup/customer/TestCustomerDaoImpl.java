package com.fdmgroup.customer;

import static org.junit.Assert.*;
//import static org.mockito.Matchers.intThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.Customer;
import com.fdmgroup.daoImpl.CustomerDaoImpl;

public class TestCustomerDaoImpl {

	private Customer tempCustomer1, tempCustomer2, tempCustomer3;
	private CustomerDaoImpl targetCustomerDaoImpl;
	// 14 + 2

	@Before
	public void setUp() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from ng_customer").executeUpdate();
		transaction.commit();

		targetCustomerDaoImpl = new CustomerDaoImpl();

		tempCustomer1 = new Customer(1, "Customer1", "password1", "Ship Yard, London, L12 3RQ", "cust@hotmail.com",
				"Ship Yard, London, L12 3RQ", "customer10");
		tempCustomer2 = new Customer(2, "John", "password2", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com",
				"Birmingham street, Birmingham, B32 9LP", "john234");
		tempCustomer3 = new Customer(3, "Potter", "password3", "1/4 Street Palace", "harry.magic@gmail.com", 
				"1/4 Street Palace", "pot780");
	}

	@Test
	public void test_listOfCustomersRetrunsEmptyListWhenNoCustomersAdded() {

		// assertEquals(0, targetCustomerDaoImpl.listOfCustomers().size());
		// or as under
		assertTrue(targetCustomerDaoImpl.listOfCustomers().isEmpty());
	}

	@Test
	public void test_listOfCustomersReturnsListWithsizeOneWhenOneCustomerAdded() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		assertEquals(1, targetCustomerDaoImpl.listOfCustomers().size());
	}

	@Test
	public void test_WhenOneCustomerAddedThen_listOfCustomersReturnsListWithSameCustomer() {

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
		Customer customer = targetCustomerDaoImpl.getCustomer(2);
		String actual = customer.getCustomerName();
		assertEquals(expected, actual);

	}

	@Test
	public void test_getCustomerReturnsCustomerHavingMatchingAddressAsRequired() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		String expected = tempCustomer1.getAddress();
		String actual = targetCustomerDaoImpl.getCustomer(1).getAddress();
		assertEquals(expected, actual);
	}

	@Test
	public void test_removeCustomerRemovesACustomerFromTheList_AndReducesSizeOfReturnedListOfCustomerByOne() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		// int initialSizeOfList =
		// targetCustomerDaoImpl.listOfCustomers().size();
		targetCustomerDaoImpl.removeCustomer(1);
		// assertEquals((initialSizeOfList - 1),
		// targetCustomerDaoImpl.listOfCustomers().size());
		assertEquals(1, targetCustomerDaoImpl.listOfCustomers().size());

	}

	@Test
	public void test_listOFCusotmerAcceptNewCustomerAfterRemovingCustomerFromTheList() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.removeCustomer(1);

		targetCustomerDaoImpl.addCustomer(tempCustomer3);

		int expected = 2;
		int actual = targetCustomerDaoImpl.listOfCustomers().size();

		assertEquals(expected, actual);

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

		// Double newPriceProvided = 99.99d;
		String updatedName = "Micky";

		tempCustomer1.setCustomerName(updatedName);
		targetCustomerDaoImpl.updateCustomer(tempCustomer1);

		String expected = tempCustomer1.getCustomerName();
		String actual = targetCustomerDaoImpl.getCustomer(1).getCustomerName();

		assertEquals(expected, actual);
	}

	@Test
	public void test_updateProductUpdatesTheCustomerRecordEvenWhenMoreThanOneAttributesHaveModified() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);

		String customerNewAddress = "45 House in Brick Lane, Manchester, M35 90L";
		String newShippingAddress = "This is my Updated Shipping Address";
		String newEmailAddress = "newEmailAddress@fmdgroup.com";

		tempCustomer1.setAddress(customerNewAddress);
		tempCustomer1.setAddress(newShippingAddress);
		tempCustomer1.setEmail(newEmailAddress);

		targetCustomerDaoImpl.updateCustomer(tempCustomer1);

		// assertEquals(tempCustomer1,
		// targetCustomerDaoImpl.listOfCustomers().get(0));
		assertEquals(tempCustomer1, targetCustomerDaoImpl.getCustomer(1));
	}

	// @Test
	public void test_updateCustomerUpdatesTheCustomertAddressAsPerNewAddressProvided() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);

		String customerNewAddress = "45 House in Brick Lane, Manchester, M35 90L";

		tempCustomer3.setAddress(customerNewAddress);
		targetCustomerDaoImpl.updateCustomer(tempCustomer3);

		String expected = tempCustomer3.getAddress();
		String actual = targetCustomerDaoImpl.getCustomer(3).getAddress();

		assertEquals(expected, actual);
	}

	// @Test
	public void test_updateCustomerUpdatesTheCustomerShippingAddressAsPerNewShippingAddressProvided() {

		targetCustomerDaoImpl.addCustomer(tempCustomer2);

		String newShippingAddress = "This is my Updated Shipping Address";
		tempCustomer2.setAddress(newShippingAddress);
		targetCustomerDaoImpl.updateCustomer(tempCustomer2);

		String expected = tempCustomer2.getShipAddress();
		String actual = targetCustomerDaoImpl.getCustomer(2).getShipAddress();

		assertEquals(expected, actual);
	}

	@Test
	public void test_updateCustomerUpdatesTheCustomerInfomrationWhenCustomerExistsInList_andDoesNotCreateAnyMoreRows() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		String newShippingAddress = "This is my Updated Shipping Address";
		tempCustomer1.setShipAddress(newShippingAddress);
		targetCustomerDaoImpl.updateCustomer(tempCustomer1);
		assertEquals(1, targetCustomerDaoImpl.listOfCustomers().size());
	}

	@Test
	public void test_AfterRemovingLastCustomerFromTheList_theListOfCustomersReturnsAnEmptyList() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1); // size = 1
		targetCustomerDaoImpl.removeCustomer(1);
		int expected = targetCustomerDaoImpl.listOfCustomers().size();
		assertEquals(0, expected);
	}

	@Test
	public void test_removeCustoemrRemovesASpecificCustomerFromTheListWithoutModifyingAnyOtherRecords() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);
		targetCustomerDaoImpl.addCustomer(tempCustomer3);

		targetCustomerDaoImpl.removeCustomer(1);

		// assertEquals(2, tempCustomer3.getCustomerNumber());
		// assertEquals(tempCustomer3,
		// targetCustomerDaoImpl.listOfCustomers().get(1));

		assertEquals(tempCustomer3, targetCustomerDaoImpl.getCustomer(3));
	}

	@Test
	public void test_removeCustomerDoesNotRemoveAnyCustomerFromListIfTheProvidedIndexDoesNotExist() {

		targetCustomerDaoImpl.addCustomer(tempCustomer1);
		targetCustomerDaoImpl.addCustomer(tempCustomer2);

		targetCustomerDaoImpl.removeCustomer(33); // does not exist

		assertEquals(2, targetCustomerDaoImpl.listOfCustomers().size());
	}

	@AfterClass
	public static void clear() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from ng_customer").executeUpdate();
		transaction.commit();

	}
}
