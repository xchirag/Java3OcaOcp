package com.fdmgroup.daoImpl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.daoImpl.CustomerDaoImpl;
import com.fdmgroup.entity.Customer;
import com.fdmgroup.entity.Product;
import com.fdmgroup.entity.ShoppingBasket;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

		String userName = "nick7";
		List<Customer> cusomerByName = customerDaoImpl.getCusomerByName(userName);
		System.out.println(cusomerByName.size());
		// System.out.println();

		for (Customer customer : cusomerByName) {
			System.out.println(customer);

		}

		String joinQuery = "SELECT s FROM ng_shoppingbasket s";

		/*
		 * String joinQuery =
		 * "select ngs.basketPrice, ngs.discountPercentage, ngs.discountedBasketPrice, ngc.customerName "
		 * + "from ng_shoppingbasket ngs inner join ng_customer ngc";
		 */
		// above query works wihtout error and provide cartesian product!

		/*
		 * String joinQuery =
		 * "select ngs.basketId, ngc.customerName, ngsp.id, ngp.productName, " +
		 * "ngsp.Quantity, ngs.basketPrice, ngs.discountPercentage, ngs.discountedBasketPrice "
		 * +
		 * "from ng_shoppingbasket_products ngsp inner join ngsp.ng_shoppingbasket ngs inner join ngs.ng_customer ngc  inner join ngc.ng_product ngp"
		 * ;
		 */

		// String joinQuery = "select ngs.basketId, ngsp.id from
		// ng_shoppingbasket_products ngsp inner join ngsp.ng_shoppingbasket
		// ngs";

		/*
		 * String joinQuery =
		 * "select ngs.basketId, ngc.customerName, ngp.id, ngp.productName, " +
		 * "ngs.basketPrice, ngs.discountPercentage, ngs.discountedBasketPrice "
		 * +
		 * "from ng_shoppingbasket ngs join ng_customer ngc  join ng_product ngp"
		 * ;
		 */

		// this works
		/*
		 * String joinQuery = "select ngs.basketId, ngc.customerName, " +
		 * "ngs.basketPrice, ngs.discountPercentage, ngs.discountedBasketPrice "
		 * + "from ng_shoppingbasket ngs join ngs.customer ngc";
		 */

		/*
		 * String joinQuery = "select ngs.basketId, ngc.customerName, " +
		 * "ngs.basketPrice, ngs.discountPercentage, ngs.discountedBasketPrice "
		 * + "from ng_shoppingbasket ngs join ngs.customer ngc";
		 */

		// joinQuery.length();

		// ngs.listOfAddedItems.Quantity,
		// join ngs.listOfAddedItems ngsp

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		Query createQuery = entityManager.createQuery(joinQuery);
		List resultList = createQuery.getResultList();

		Runner runner = new Runner();

		runner.displayA(resultList);

		// add chair quantity 1 for Customer Name: vik2
		Iterator iterator_2 = resultList.iterator();
		while (iterator_2.hasNext()) {
			ShoppingBasket shoppingBasket = (ShoppingBasket) iterator_2.next();
			if (shoppingBasket.getCustomer().getCustomerName().equals("vik2")) {
				Map<Product, Integer> listOfAddedItemsOfVik = shoppingBasket.getListOfAddedItems();
				Iterator<Entry<Product, Integer>> iterateMapOfVik = listOfAddedItemsOfVik.entrySet().iterator();
				while (iterateMapOfVik.hasNext()) {
					Entry<Product, Integer> productMap = iterateMapOfVik.next();
					if (productMap.getKey().getProductName().equals("chair")) {
						Integer x = productMap.getValue();
						x++;
						productMap.setValue(x);
					}
				}
			}

		}
		runner.displayA(resultList);

		/*
		 * while(iterator.hasNext()){ ShoppingBasket nextS = (ShoppingBasket)
		 * iterator.next(); System.out.println( "basket Id: " +
		 * next.getBasketId()); System.out.println( "basket price: "
		 * +next.getBasketPrice()); System.out.println( "Customer Number: "+
		 * next.getCustomer().getCustomerNumber()); System.out.println(
		 * "Customer Name: "+ next.getCustomer().getCustomerName());
		 * Map<Product, Integer> listOfAddedItems = next.getListOfAddedItems();
		 * System.out.println("list of Added items size " +
		 * listOfAddedItems.size()); Iterator<Entry<Product, Integer>>
		 * iteratorMap = listOfAddedItems.entrySet().iterator();
		 * while(iteratorMap.hasNext()){ Entry<Product, Integer> entry =
		 * iteratorMap.next(); System.out.print("product name: " +
		 * entry.getKey().getProductName()); System.out.print(" id: "+
		 * entry.getKey().getId()); System.out.print(" price: " +
		 * entry.getKey().getPrice()); System.out.print(" quantity: " +
		 * entry.getValue()); System.out.println(); }
		 */

	}

	// System.out.println(resultList.size());

	/*
	 * Query createQuery = entityManager.createQuery(joinQuery); List resultList
	 * = createQuery.getResultList();
	 */

	// System.out.println("size of resultList is" + resultList.size());

	// System.out.println(new Random().nextInt(10000));

	// LocalDate date = new LocalDate();
	// System.out.println(LocalDate.now());

	private void displayA(List resultList) {
		Iterator iterator = resultList.iterator();
		int b = 0;
		int size = 1;

		while (b < size && iterator.hasNext()) {
			b = b + 1;
			ShoppingBasket next = (ShoppingBasket) iterator.next();
			System.out.println("basket Id: " + next.getBasketId());
			System.out.println("basket price: " + next.getBasketPrice());
			System.out.println("Customer Number: " + next.getCustomer().getCustomerNumber());
			System.out.println("Customer Name: " + next.getCustomer().getCustomerName());
			Map<Product, Integer> listOfAddedItems = next.getListOfAddedItems();
			System.out.println("list of Added items size " + listOfAddedItems.size());
			Iterator<Entry<Product, Integer>> iteratorMap = listOfAddedItems.entrySet().iterator();
			while (iteratorMap.hasNext()) {
				Entry<Product, Integer> entry = iteratorMap.next();
				System.out.print("product name: " + entry.getKey().getProductName());
				System.out.print(" id: " + entry.getKey().getId());
				System.out.print(" price: " + entry.getKey().getPrice());
				System.out.print(" quantity: " + entry.getValue());
				System.out.println();
			}
			System.out.println(b + "     :   ");
		}
	}

}

// https://stackoverflow.com/questions/20056847/jpql-the-state-field-path-cannot-be-resolved-to-a-valid-type
