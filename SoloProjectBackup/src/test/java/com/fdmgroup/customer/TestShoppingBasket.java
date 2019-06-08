package com.fdmgroup.customer;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.Customer;
import com.fdmgroup.entity.Product;
import com.fdmgroup.entity.ShoppingBasket;
import com.fdmgroup.daoImpl.ProductDaoImpl;
import com.fdmgroup.daoImpl.ShoppingBasketDaoImpl;
//import com.fdmgroup.customerDao.Customer;
//import com.fdmgroup.productDao.Product;

public class TestShoppingBasket {

	private Customer john, potter, harry;
	private Product randomProduct, keyboard, mouse;
	private ShoppingBasket basketOfJohn, basketOfPotter, basketOfHarry;
	private Map<Product, Integer> mapOfItemsOne, mapOfItemsTwo;
	private ShoppingBasketDaoImpl targetShoppingBasketDaoImpl;

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();

	@Before
	public void setUp() {

		// EntityManagerFactory entityManagerFactory =
		// Persistence.createEntityManagerFactory("jdbcProject3");
		// EntityManager entityManager =
		// entityManagerFactory.createEntityManager();
		// EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		entityManager.createNativeQuery("delete from ng_shoppingbasket_products").executeUpdate();
		entityManager.createQuery("delete from ng_shoppingbasket").executeUpdate();
		entityManager.createQuery("delete from ng_customer").executeUpdate();
		entityManager.createQuery("delete from ng_product").executeUpdate();

		transaction.commit();

		// System.out.println(1);

		john = new Customer(1, "John", "password1", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com",
				"Birmingham street, Birmingham, B32 9LP", "john23");
		potter = new Customer(2, "Potter", "password2", "1/4 Street Palace", "harry.magic@gmail.com", "1/4 Street Palace", "pot23");
		harry = new Customer(3, "Harry", "password3", "Hairy Road East, NewYork", "dumbdelor@webmail.com",
				"Hairy Road East, NewYork", "harry90");

		randomProduct = new Product(1, "RandomProduct", 15);
		keyboard = new Product(2, "keyBoard", 25);
		mouse = new Product(3, "mouse", 5);

		ProductDaoImpl pro = new ProductDaoImpl();
		pro.addProduct(randomProduct);
		pro.addProduct(keyboard);
		pro.addProduct(mouse);

		mapOfItemsOne = new HashMap<Product, Integer>();
		mapOfItemsTwo = new HashMap<Product, Integer>();

		mapOfItemsOne.put(keyboard, 2);
		mapOfItemsOne.put(mouse, 1);

		mapOfItemsTwo.put(randomProduct, 5);

		basketOfJohn = new ShoppingBasket(10, john, 55d, 10, 49.5d, mapOfItemsOne);
		basketOfPotter = new ShoppingBasket(20, potter, 75d, 10, 67.5d, mapOfItemsTwo);
		basketOfHarry = new ShoppingBasket(30, harry, 55d, 10, 49.5d, mapOfItemsOne);

		targetShoppingBasketDaoImpl = new ShoppingBasketDaoImpl();
		// System.out.println(1);
		entityManager.detach(keyboard);
		entityManager.detach(mouse);
		entityManager.detach(randomProduct);
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
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.getShoppingBasket(10));
		// this assert requires hasCode() and equals() implementation in
		// ShoppingBasket class
	}

	@Test
	public void test_getShoppingBasketReturnsABasketAsPerBasketIndexProvided() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);

		int expected = basketOfPotter.getBasketId();
		int actual = targetShoppingBasketDaoImpl.getShoppingBasket(20).getBasketId();

		assertEquals(expected, actual);

	}

	@Test
	public void test_priceOfBasketReturnedByGetShoppingBasketMatchesWithPriceOfThatBasket() {
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		double expected = basketOfJohn.getBasketPrice();
		// Double actual =
		// targetShoppingBasketDaoImpl.listOfBaskets().get(0).getBasketPrice();
		double actual = targetShoppingBasketDaoImpl.getShoppingBasket(10).getBasketPrice();
		assertEquals(expected, actual, 0.001);
	}

	// @Test
	public void test_removeShoppingBasketRemovesBasketFromTheList_AndReducesSizeOfReturnedListOfBasketByOne() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		targetShoppingBasketDaoImpl.removeShoppingBasket(10);
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

		targetShoppingBasketDaoImpl.removeShoppingBasket(10);
		targetShoppingBasketDaoImpl.removeShoppingBasket(20);

		int expected = 1;
		int actual = targetShoppingBasketDaoImpl.listOfBaskets().size();
		assertEquals(expected, actual);
	}

	@Test
	public void test_updateShoppingBasketUpdatesThePriceOfBasketAsPerNewBasetProvided() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);

		Map<Product, Integer> mapOfItemsOneUpdated = new HashMap<Product, Integer>();
		mapOfItemsOneUpdated.put(keyboard, 20);
		basketOfJohn.setListOfAddedItems(mapOfItemsOneUpdated);
		basketOfJohn.setBasketPrice(500d);
		targetShoppingBasketDaoImpl.updateShoppingBasket(basketOfJohn);
		
		Double basketPrice = targetShoppingBasketDaoImpl.getShoppingBasket(10).getBasketPrice();
		assertEquals(500.00, basketPrice, 0.001);
	}

	@Test
	public void test_updateShoppingBaketUpdatesQuanityOfBasketedProductAsPerNewQuanityGiven() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);
		Map<Product, Integer> mapOfItemsTwoUpdated = new HashMap<Product, Integer>();
		mapOfItemsTwoUpdated.put(randomProduct, 15);
		basketOfPotter.setListOfAddedItems(mapOfItemsTwoUpdated);
		assertEquals(15, basketOfPotter.getListOfAddedItems().get(randomProduct).intValue());

	}

	// following have to see whether it works this way or not
	//@Test
	public void test_updateShoppingBasketUpdatesTheBasketRecordEvenWhenMoreThanOneAttributesHaveBeenModified() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		Map<Product, Integer> mapNewItems = new HashMap<Product, Integer>(); // the following would not work because trying to add items while creating the items!
		//based upon nick's explanation this will not work in this way
		// the same test can be rewritten as test_2_updateShoppingBasketUpdatesTheBasketRecordEvenWhenMoreThanOneAttributesHaveBeenModified()
//		Product newProduct2 = new Product(15, "Entirely New Product Line", 7.0d);
//		mapNewItems.put(newProduct2, 3);
//		basketOfJohn.setListOfAddedItems(mapNewItems);
//		targetShoppingBasketDaoImpl.updateShoppingBasket(basketOfJohn); // added
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.listOfBaskets().get(0));
		System.out.println(111);

	}

	@Test
	public void test_2_updateShoppingBasketUpdatesTheBasketRecordEvenWhenMoreThanOneAttributesHaveBeenModified(){
		
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		
		Map<Product, Integer> mapNewItems = new HashMap<Product, Integer>();
		Product newProduct2 = new Product(15, "Entirely New Product Line", 7.0d);
		mapNewItems.put(newProduct2, 3);
				
		ProductDaoImpl pro = new ProductDaoImpl();
		pro.addProduct(newProduct2);
		
		basketOfJohn.setListOfAddedItems(mapNewItems);
		targetShoppingBasketDaoImpl.updateShoppingBasket(basketOfJohn); // added
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.listOfBaskets().get(0));
		// see now this passes!
		
	}
	
	@Test
	public void test_updateShoppingBasketUpdatesTheSpecificShoppingBasketWhenTheBasketExistsInTheList_andDoesNotCreateAnyMoreRows() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);

		Map<Product, Integer> mapOfItemsOnepdated = new HashMap<Product, Integer>();
		mapOfItemsOnepdated.put(keyboard, 1);
		mapOfItemsOnepdated.put(mouse, 2);
		mapOfItemsOnepdated.put(randomProduct, 3);

		basketOfJohn.setListOfAddedItems(mapOfItemsOnepdated);
		targetShoppingBasketDaoImpl.updateShoppingBasket(basketOfJohn);

		assertEquals(2, targetShoppingBasketDaoImpl.listOfBaskets().size());

	}

	@Test
	public void test_afterRemovingLastBasketFromTheList_theListOfBasketsReturnsAnEmptyList() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.removeShoppingBasket(10);
		assertEquals(0, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}

	@Test
	public void test_removesBasketRemovesASpecificBasketFromTheListWithoutModifyingAnyOtherRecords() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);

		targetShoppingBasketDaoImpl.removeShoppingBasket(20);
		assertEquals(basketOfJohn, targetShoppingBasketDaoImpl.getShoppingBasket(10));

	}

	@Test
	public void test_removeShoppingBasketDoesNotRemoveAnyBasketFromTheListIfTheIndexProvidedDoesNotExist() {

		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfHarry);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfJohn);
		targetShoppingBasketDaoImpl.addShoppingBasket(basketOfPotter);

		targetShoppingBasketDaoImpl.removeShoppingBasket(7); // which does not exist

		assertEquals(3, targetShoppingBasketDaoImpl.listOfBaskets().size());
	}

	@AfterClass
	public static void tearDown() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		entityManager.createNativeQuery("delete from ng_shoppingbasket_products").executeUpdate();
		entityManager.createQuery("delete from ng_shoppingbasket").executeUpdate();
		entityManager.createQuery("delete from ng_customer").executeUpdate();
		entityManager.createQuery("delete from ng_product").executeUpdate();

		transaction.commit();

	}

}
