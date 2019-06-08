package com.fdmgroup.customer;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.classimp.ShoppingBasket;
import com.fdmgroup.classimp.ShoppingBasketDaoImpl;
import com.fdmgroup.customerDao.Customer;
import com.fdmgroup.productDao.Product;

public class TestShoppingBasket {

	private Customer john, potter, harry;
	private Product randomProduct, keyboard, mouse;
	private ShoppingBasket basketOfJohn, basketOfPotter, basketOfHarry;
	private Map<Product, Integer> mapOfItemsOne, mapOfItemsTwo;
	private ShoppingBasketDaoImpl targetShoppingBasketDaoImpl;

	@Before
	public void setUp() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from XProduct").executeUpdate();
		entityManager.createQuery("delete from XCustomer").executeUpdate();
		entityManager.createQuery("delete from XShoppingBasket_Products").executeUpdate();
		entityManager.createQuery("delete from XShoppingBasket").executeUpdate();
		transaction.commit();

		john = new Customer(1, "John", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com",
				"Birmingham street, Birmingham, B32 9LP");
		potter = new Customer(2, "Potter", "1/4 Street Palace", "harry.magic@gmail.com", "1/4 Street Palace");
		harry = new Customer(3, "Harry", "Hairy Road East, NewYork", "dumbdelor@webmail.com", "Hairy Road East, NewYork");

		randomProduct = new Product(1, "RandomProduct", 15);
		keyboard = new Product(2, "keyBoard", 25);
		mouse = new Product(3, "mouse", 5);

		mapOfItemsOne = new HashMap<>();
		mapOfItemsTwo = new HashMap<>();

		mapOfItemsOne.put(keyboard, 2);
		mapOfItemsOne.put(mouse, 1);

		mapOfItemsTwo.put(randomProduct, 5);

		basketOfJohn = new ShoppingBasket(10, john, 55d, 10, 49.5d, mapOfItemsOne);
		basketOfPotter = new ShoppingBasket(20, potter, 75d, 10, 67.5d, mapOfItemsTwo);
		basketOfHarry = new ShoppingBasket(30, harry, 55d, 10, 49.5d, mapOfItemsOne);

		targetShoppingBasketDaoImpl = new ShoppingBasketDaoImpl();
	}

	@Test
	public void test_listOfBasketsReturnEmptyListWhenNoBasketAdded() {
		assertEquals(0, targetShoppingBasketDaoImpl.listOfBaskets().size());

	}

	@Test
	public void test_listOfBasketReturnsListWithTheSizeOneWhenOneBasketAdded() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		assertEquals(1, targetShoppingBasketDaoImpl.listOfBaskets().size());

	}

	@Test
	public void test_listOfBaksetReturnsListOfSizeTwo_WhenTwoBasketsAdded() {
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		assertEquals(2, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}

	@Test
	public void test_theListReturnByListOfBasketContainsBasketTypeObject() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.getShoppingBasket(0));
		// this assert requires hasCode() and equals() implementation in
		// ShoppingBasket class
	}

	/*
	 * @Test public void test_getCustomerReturnsACustomerAsPerIndexProvided() {
	 * 
	 * targetCustomerDaoImpl.addCustomer(tempCustomer1);
	 * targetCustomerDaoImpl.addCustomer(tempCustomer2);
	 * targetCustomerDaoImpl.addCustomer(tempCustomer3);
	 * 
	 * String expected = tempCustomer2.getCustomerName(); String actual =
	 * targetCustomerDaoImpl.getCustomer(1).getCustomerName();
	 * assertEquals(expected, actual);
	 * 
	 * }
	 * 
	 */

	@Test
	public void test_getShoppingBasketReturnsABasketAsPerBasketIndexProvided() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);

		int expected = basketOfPotter.getBasketId();
		int actual = targetShoppingBasketDaoImpl.getShoppingBasket(1).getBasketId();

		assertEquals(expected, actual);

	}

	@Test
	public void test_priceOfBasketReturnedByGetShoppingBasketMatchesWithPriceOfThatBasket() {
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		double expected = basketOfJohn.getBasketPrice();
		// Double actual =
		// targetShoppingBasketDaoImpl.listOfBaskets().get(0).getBasketPrice();
		double actual = targetShoppingBasketDaoImpl.getShoppingBasket(0).getBasketPrice();
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public void test_removeShoppingBasketRemovesBasketFromTheList_AndReducesSizeOfReturnedListOfBasketByOne() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		
		targetShoppingBasketDaoImpl.removeShoppingBasket(0);
		
		assertEquals(1, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}

	
	@Test
	public void test_listOfBasketAcceptsNewBasketAfterRemovingBasketFromTheList() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);

		targetShoppingBasketDaoImpl.removeShoppingBasket(0);

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);

		assertEquals(2, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}

	@Test
	public void test_ifRemoveShoppingBasketHasCalledTwiceThenTheReturnedListOfBaksetWouldHaveSizeReducedByTwo() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfHarry);
		

		targetShoppingBasketDaoImpl.removeShoppingBasket(0);
		targetShoppingBasketDaoImpl.removeShoppingBasket(1);

		int expected = 1;
		int actual = targetShoppingBasketDaoImpl.listOfBaskets().size();
		assertEquals(expected, actual);
	}
	
	/*
	 * @Test
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
	 */
	
	@Test
	public void test_updateShoppingBasketUpdatesThePriceOfBasketAsPerNewBasetProvided(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		
		Map<Product, Integer> mapOfItemsOneUpdated = new HashMap<>();
		mapOfItemsOneUpdated.put(keyboard, 2);
		basketOfJohn.setListOfAddedItems(mapOfItemsOneUpdated);
		//System.out.println(basketOfJohn.getListOfAddedItems().get(keyboard));
		assertEquals(50.00, targetShoppingBasketDaoImpl.listOfBaskets().get(0).getBasketPrice(), 0.001);
	}

	//@Test
	public void test_updateShoppingBaketUpdatesQuanityOfBasketedProductAsPerNewQuanityGiven(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		Map<Product, Integer> mapOfItemsTwoUpdated = new HashMap<>();
		mapOfItemsTwoUpdated.put(randomProduct, 15);
		basketOfPotter.setListOfAddedItems(mapOfItemsTwoUpdated);
		assertEquals(15, basketOfPotter.getListOfAddedItems().get(randomProduct).intValue());
		
	}
	
	//  following have to see whether it works this way or not
	@Test
	public void test_updateShoppingBasketUpdatesTheBasketRecordEvenWhenMoreThanOneAttributesHaveBeenModified(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		Map<Product, Integer> mapNewItems = new HashMap<>();
		Product newProduct = new Product(15, "Entirely New Product Line", 7.0d);
		mapNewItems.put(newProduct, 3);
		basketOfJohn.setListOfAddedItems(mapNewItems);
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.listOfBaskets().get(0));
		
	}
	
	@Test
	public void test_updateShoppingBasketUpdatesTheSpecificShoppingBasketWhenTheBasketExistsInTheList_andDoesNotCreateAnyMoreRows(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		
		Map<Product, Integer> mapOfItemsOnepdated = new HashMap<>();
		mapOfItemsOnepdated.put(keyboard, 1);
		mapOfItemsOnepdated.put(mouse, 2);
		mapOfItemsOnepdated.put(randomProduct, 3);
		
		basketOfJohn.setListOfAddedItems(mapOfItemsOnepdated);
		targetShoppingBasketDaoImpl.updateShoppingBasket(basketOfJohn);
		
		assertEquals(2, targetShoppingBasketDaoImpl.listOfBaskets().size());
		
		
	}

	@Test
	public void test_afterRemovingLastBasketFromTheList_theListOfBasketsReturnsAnEmptyList(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.removeShoppingBasket(0);
		assertEquals(0, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}

	@Test
	public void test_removesBasketRemovesASpecificBasketFromTheListWithoutModifyingAnyOtherRecords(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		
		targetShoppingBasketDaoImpl.removeShoppingBasket(1);
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.getShoppingBasket(0));
		
	}
	
	@Test
	public void test_removeShoppingBasketDoesNotRemoveAnyBasketFromTheListIfTheIndexProvidedDoesNotExist(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfHarry);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		
		targetShoppingBasketDaoImpl.removeShoppingBasket(7); // which does not exist
		
		assertEquals(3, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}
	
	
}
