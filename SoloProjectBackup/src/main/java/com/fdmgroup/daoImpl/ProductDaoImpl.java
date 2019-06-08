package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fdmgroup.Dao.ProductDao;
import com.fdmgroup.entity.Product;

public class ProductDaoImpl implements ProductDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public void setupEnvironment() {

		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();

	}

//	@Override
	public void addProduct(Product product) {

		setupEnvironment();
		// Customer tempCustomer = entityManager.find(Customer.class,
		// customer.getCustomerNumber());
		//
		// if (tempCustomer == null) {
		// transaction.begin();
		// entityManager.merge(customer);
		// transaction.commit();
		// }

		Product tempProduct = entityManager.find(Product.class, product.getId());
		if (tempProduct == null) {

			transaction.begin();
			entityManager.merge(product);
			transaction.commit();
		}

	}

//	@Override
	public Product getProduct(int productId) {
		setupEnvironment();
		// Customer customer = entityManager.find(Customer.class,
		// CustomerNumber);
		// return customer;

		Product product = entityManager.find(Product.class, productId);
		return product;
	}

//	@Override
	public void removeProduct(int productId) {
		setupEnvironment();
		Product product = entityManager.find(Product.class, productId);
		if (product != null) {
			transaction.begin();
			entityManager.remove(product);
			transaction.commit();
		}
		// Customer customer = entityManager.find(Customer.class, customerId);
		// if (customer != null) {
		// transaction.begin();
		// entityManager.remove(customer);
		// transaction.commit();
		// }
	}
	
	/*
	 * @Override
	public void updateCustomer(Customer customer) {

		// EntityManagerFactory entityManagerFactory =
		// Persistence.createEntityManagerFactory("jdbcProject3");
		// EntityManager entityManager =
		// entityManagerFactory.createEntityManager();
		// EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		int customerNumber = customer.getCustomerNumber();
		Customer tempCustomer = entityManager.find(Customer.class, customerNumber);

		if (tempCustomer != null) {
			transaction.begin();
			entityManager.merge(customer);
			transaction.commit();
		}
	}
	 */

//	@Override
	public void updateProduct(Product product) {
		
		setupEnvironment();
		int productId = product.getId();

		Product tempProduct = entityManager.find(Product.class, productId);
		if (tempProduct != null) {
			transaction.begin();
			entityManager.merge(product);
			transaction.commit();

		}
	}

//	@Override

	public List<Product> listOfProducts() {
		// TODO Auto-generated method stub
		// return (new ArrayList<>());
		// return null;

		setupEnvironment();

		List<Product> resultList = entityManager.createQuery("select a from ng_product a", Product.class).getResultList();
		return resultList;
	}
	

}

/*package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fdmgroup.Dao.ProductDao;
import com.fdmgroup.entity.Customer;
import com.fdmgroup.entity.Product;

public class ProductDaoImpl implements ProductDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public void setupEnvironment() {

		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();

	}

	@Override
	public void addProduct(Product product) {

		setupEnvironment();
		// Customer tempCustomer = entityManager.find(Customer.class,
		// customer.getCustomerNumber());
		//
		// if (tempCustomer == null) {
		// transaction.begin();
		// entityManager.merge(customer);
		// transaction.commit();
		// }

		Product tempProduct = entityManager.find(Product.class, product.getId());
		if (tempProduct == null) {

			transaction.begin();
			entityManager.merge(product);
			transaction.commit();
		}

	}

	@Override
	public Product getProduct(int productId) {
		setupEnvironment();
		// Customer customer = entityManager.find(Customer.class,
		// CustomerNumber);
		// return customer;

		Product product = entityManager.find(Product.class, productId);
		return product;
	}

	@Override
	public void removeProduct(int productId) {
		setupEnvironment();
		Product product = entityManager.find(Product.class, productId);
		if (product != null) {
			transaction.begin();
			entityManager.remove(product);
			transaction.commit();
		}
		// Customer customer = entityManager.find(Customer.class, customerId);
		// if (customer != null) {
		// transaction.begin();
		// entityManager.remove(customer);
		// transaction.commit();
		// }
	}
	
	
	 * @Override
	public void updateCustomer(Customer customer) {

		// EntityManagerFactory entityManagerFactory =
		// Persistence.createEntityManagerFactory("jdbcProject3");
		// EntityManager entityManager =
		// entityManagerFactory.createEntityManager();
		// EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		int customerNumber = customer.getCustomerNumber();
		Customer tempCustomer = entityManager.find(Customer.class, customerNumber);

		if (tempCustomer != null) {
			transaction.begin();
			entityManager.merge(customer);
			transaction.commit();
		}
	}
	 

	@Override
	public void updateProduct(Product product) {
		
		setupEnvironment();
		int productId = product.getId();

		Product tempProduct = entityManager.find(Product.class, productId);
		if (tempProduct != null) {
			transaction.begin();
			entityManager.merge(product);
			transaction.commit();

		}
	}

	@Override

	public List<Product> listOfProducts() {
		// TODO Auto-generated method stub
		// return (new ArrayList<>());
		// return null;

		setupEnvironment();

		List<Product> resultList = entityManager.createQuery("select a from ng_product a", Product.class).getResultList();
		return resultList;
	}
	

}
*/