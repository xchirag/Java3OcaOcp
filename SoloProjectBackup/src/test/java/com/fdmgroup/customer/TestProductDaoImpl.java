package com.fdmgroup.customer;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.Customer;
import com.fdmgroup.entity.Product;
import com.fdmgroup.daoImpl.ProductDaoImpl;

public class TestProductDaoImpl {

	private ProductDaoImpl targetProductDaoImpl;// = new ProductDaoImpl();
	private Product tempProduct1, tempProduct2, tempProduct3;
	//15 + 2

	@Before
	public void setup() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from ng_product").executeUpdate();
		transaction.commit();
		// must commit to database if any minor change happen to database or not
		// otherwise in different state the Junit test will run differently

		tempProduct1 = new Product(1, "RandomProduct", 15);
		tempProduct2 = new Product(2, "keyBoard", 25);
		tempProduct3 = new Product(3, "mouse", 5);

		targetProductDaoImpl = new ProductDaoImpl();

	}

	@Test
	public void test_listOfProductsRetrunsEmptyListWhenNoProductsAdded() {

		// or another way as under
		// List<Product> temp = targetProductDaoImpl.listOfProducts();
		// assertTrue(temp.isEmpty());

		assertEquals(0, targetProductDaoImpl.listOfProducts().size());
		assertTrue(targetProductDaoImpl.listOfProducts().isEmpty());
	}

	@Test
	public void test_listOfProductReturnsListWithsizeOneWhenOneProductAdded() {

		targetProductDaoImpl.addProduct(tempProduct1);
		assertEquals(1, targetProductDaoImpl.listOfProducts().size());
	}

	@Test
	public void test_listOfProductReturnsListWithSameProductAsTheOneAdded() {

		targetProductDaoImpl.addProduct(tempProduct1);
		assertEquals(tempProduct1, targetProductDaoImpl.listOfProducts().get(0));
	}

	@Test
	public void test_listOfProductReturnsListOfSizeTwo_WhenTwoProductsAdded() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		assertEquals(2, targetProductDaoImpl.listOfProducts().size());
	}

	@Test
	public void test_addProductAddsOneProductIntoList_andIncreasesSizeOfReturnedListByOne() {

		int initSize = targetProductDaoImpl.listOfProducts().size();
		targetProductDaoImpl.addProduct(tempProduct2);
		assertEquals((initSize + 1), targetProductDaoImpl.listOfProducts().size());
	}

	@Test
	public void test_getProductReturnsAProductAsPerIndexProvided() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		targetProductDaoImpl.addProduct(tempProduct3);

		String expected = tempProduct2.getProductName();
		String actual = targetProductDaoImpl.getProduct(2).getProductName();
		assertEquals(expected, actual);

	}

	@Test
	public void test_getProductReturnsCorrectPriceOfReturnedProduct() {

		targetProductDaoImpl.addProduct(tempProduct1);
		Double expected = tempProduct1.getPrice();
		Double actual = targetProductDaoImpl.getProduct(1).getPrice();
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public void test_removeProductRemovesAProductFromTheList_AndReducesSizeOfReturnedListOfProductByOne() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		int initialSizeOfList = targetProductDaoImpl.listOfProducts().size();
		targetProductDaoImpl.removeProduct(1);
		assertEquals((initialSizeOfList - 1), targetProductDaoImpl.listOfProducts().size());

	}

	@Test
	public void test_ifRemoveProductHasCalledTwiceThenTheReturnListOfProductListSizeWouldDecreseByTwo() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		targetProductDaoImpl.addProduct(tempProduct3);
		int initialSizeOfList = targetProductDaoImpl.listOfProducts().size();
		targetProductDaoImpl.removeProduct(1);
		targetProductDaoImpl.removeProduct(2);
		assertEquals((initialSizeOfList - 2), targetProductDaoImpl.listOfProducts().size());
	}

	@Test
	public void test_updateProductUpdatesTheProductPriceAsPerNewPriceProvided() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		targetProductDaoImpl.addProduct(tempProduct3);

		Double newPriceProvided = 99.99d;

		tempProduct1.setPrice(newPriceProvided);
		targetProductDaoImpl.updateProduct(tempProduct1);

		double expected = tempProduct1.getPrice();
		double actual = targetProductDaoImpl.getProduct(1).getPrice();

		assertEquals(expected, actual, 0.00);
	}
	
//	@Test
//	public void test_updateProductUpdatesTheCustomerRecordEvenWhenMoreThanOneAttributesHaveModified()
	
	@Test
	public void test_updateProductUpdatesTheProductRecordEvenWhenMorethanOneAttributesHaveModified(){
		
		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		targetProductDaoImpl.addProduct(tempProduct3);
		
		//int updatedId = 7;
		String updatedName = "WireLess Mouse";
		Double updatedPrice = 25.50d;
		
		// tempProduct2.setId(updatedId); do not play with primary key field
		tempProduct3.setProductName(updatedName);
		tempProduct3.setPrice(updatedPrice);
		
		targetProductDaoImpl.updateProduct(tempProduct3);
		
		//assertEquals(tempProduct2, targetProductDaoImpl.listOfProducts().get(1));
		assertEquals(tempProduct3, targetProductDaoImpl.getProduct(3));
		//this would require hashCode and equal method into Product
		
	}
	
	

	//@Test
	public void test_updateProductUpdatesTheProductNameAsPerNewNameProvided() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		targetProductDaoImpl.addProduct(tempProduct3);

		String newProductName = "WireLess Mouse";

		tempProduct3.setProductName(newProductName);
		targetProductDaoImpl.updateProduct(tempProduct3);

		String expected = tempProduct3.getProductName();
		String actual = targetProductDaoImpl.getProduct(3).getProductName();

		assertEquals(expected, actual);
	}

	//@Test
	public void test_updateProductUpdatesTheProductIdAsPerNewIdProvided() {

		targetProductDaoImpl.addProduct(tempProduct3);
		int updatedId = 7;
		tempProduct3.setId(updatedId);
		targetProductDaoImpl.updateProduct(tempProduct3);

		int expected = tempProduct3.getId();
		int actual = targetProductDaoImpl.getProduct(7).getId();
		assertEquals(expected, actual);
	}

	@Test
	public void test_updateProductUpdatesTheSpecificProductWhenTheProductExistsInTheList_andDoesNotCreateAnyMoreRecords() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		tempProduct2.setProductName("UpdatedproductName");
		targetProductDaoImpl.updateProduct(tempProduct2);
		assertEquals(2, targetProductDaoImpl.listOfProducts().size());
	}

	@Test
	public void test_afterRemovingLastProductFromTheList_theListOfProductsReturnsEmptyList() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.removeProduct(1);
		assertEquals(0, targetProductDaoImpl.listOfProducts().size());
	}

	@Test
	public void test_removeProductRemovesASpecificProductFromTheListWithoutModifyingAnyOtherRecords() {

		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		targetProductDaoImpl.addProduct(tempProduct3);

		targetProductDaoImpl.removeProduct(2);

		assertEquals(tempProduct1, targetProductDaoImpl.getProduct(1));
		// this would require equals and hashCode implemented in Product
	}

	@Test
	public void test_listOfProductAcceptNewProducctAfterRemovingProductFromTheList() {
		
		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.addProduct(tempProduct2);
		
		targetProductDaoImpl.removeProduct(1);
		
		targetProductDaoImpl.addProduct(tempProduct3);
		
		assertEquals(2, targetProductDaoImpl.listOfProducts().size());

	}
	
	@Test
	public void test_removeProductDoesNotRemoveAnyProductFromTheListIfTheIndexProvidedDoesNotExist(){
		
		targetProductDaoImpl.addProduct(tempProduct1);
		targetProductDaoImpl.removeProduct(2);
		
		assertEquals(1, targetProductDaoImpl.listOfProducts().size());
	}
	
	@AfterClass
	public static void clear(){
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		entityManager.createQuery("delete from ng_product").executeUpdate();
		transaction.commit();
	}

}


