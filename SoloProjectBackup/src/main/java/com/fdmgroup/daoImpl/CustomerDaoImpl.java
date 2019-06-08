package com.fdmgroup.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.w3c.dom.ls.LSInput;

import com.fdmgroup.Dao.CustomerDao;
import com.fdmgroup.entity.Customer;

public class CustomerDaoImpl implements CustomerDao {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	
	public void setupEnvironment(){
		
		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

//	@Override
	public void addCustomer(Customer customer) {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerNumber());

		if (tempCustomer == null) {
			transaction.begin();
			entityManager.merge(customer);
			transaction.commit();
		}
	}

//	@Override
	public Customer getCustomer(int CustomerNumber) {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		Customer customer = entityManager.find(Customer.class, CustomerNumber);
		//String sql = "select CustomerNumber, Customer_Name, address, email, shipAddress from ng_customer where CustomerNumber = ?";
		//entityManager.createQuery(sql);
		
		return customer;
	}

//	@Override
	public void removeCustomer(int customerId) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		Customer customer = entityManager.find(Customer.class, customerId);
		if (customer != null) {
			transaction.begin();
			entityManager.remove(customer);
			transaction.commit();
		}
	}

//	@Override
	public void updateCustomer(Customer customer) {
	
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		int customerNumber = customer.getCustomerNumber();
		Customer tempCustomer = entityManager.find(Customer.class, customerNumber);
		
		if(tempCustomer != null){
			transaction.begin();
			entityManager.merge(customer);
			transaction.commit();
		}
	}

//	@Override
	public List<Customer> listOfCustomers() {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		List<Customer> resultList = entityManager.createQuery("Select a From ng_customer a", Customer.class)
				.getResultList();
		return resultList;

	}
	
	public List<Customer> getCusomerByName(String username){
		
		setupEnvironment(); 
		TypedQuery<Customer> query = entityManager.createQuery("select n from ng_customer n where n.userName = :name", Customer.class);
		return query.setParameter("name", username).getResultList();
	}

}


//List<Customer> resultByName;// = new ArrayList<Customer>();

		//String sqlQuery = "select CustomerNumber, Customer_Name, address, email, shipAddress from ng_customer where Customer_name = ?";
		//entityManager.createQuery(sqlQuery);
		
//		String sql= "SELECT c FROM ng_customer c WHERE Customer_Name LIKE :cname";
//		Query createQuery = entityManager.createQuery(sql, Customer.class);
//		createQuery.setParameter("cname", username);
//		resultByName = createQuery.getResultList();
//		
//		//resultByName = (List<Customer>) entityManager.createQuery(sql).setParameter("cname", username).getResultList();
//		return resultByName;
//		return entityManager.createQuery("select c from ng_customer c where c.Customer_Name = "ng")
//				.setParameter("cname", username)
//				.getResultList();
		
//		String sqlQuery = "select n from ng_customer n";
//		String sqlQuery2 = "select n from ng_customer n where n.Customer_Name like 'ng' ";
//		return entityManager.createQuery(sqlQuery2).getResultList();
		
		
//		TypedQuery<Country> query = em.createQuery(
//		        "SELECT c FROM Country c WHERE c.name = :name", Country.class);
//		    return query.setParameter("name", name).getSingleResult();
		    
/*package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fdmgroup.Dao.CustomerDao;
import com.fdmgroup.entity.Customer;

public class CustomerDaoImpl implements CustomerDao {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	
	public void setupEnvironment(){
		
		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

	@Override
	public void addCustomer(Customer customer) {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerNumber());

		if (tempCustomer == null) {
			transaction.begin();
			entityManager.merge(customer);
			transaction.commit();
		}
	}

	@Override
	public Customer getCustomer(int CustomerNumber) {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		Customer customer = entityManager.find(Customer.class, CustomerNumber);
		//String sql = "select CustomerNumber, Customer_Name, address, email, shipAddress from ng_customer where CustomerNumber = ?";
		//entityManager.createQuery(sql);
		
		return customer;
	}

	@Override
	public void removeCustomer(int customerId) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		Customer customer = entityManager.find(Customer.class, customerId);
		if (customer != null) {
			transaction.begin();
			entityManager.remove(customer);
			transaction.commit();
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
	
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		int customerNumber = customer.getCustomerNumber();
		Customer tempCustomer = entityManager.find(Customer.class, customerNumber);
		
		if(tempCustomer != null){
			transaction.begin();
			entityManager.merge(customer);
			transaction.commit();
		}
	}

	@Override
	public List<Customer> listOfCustomers() {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
		setupEnvironment();
		List<Customer> resultList = entityManager.createQuery("Select a From ng_customer a", Customer.class)
				.getResultList();
		return resultList;

	}

}
*/