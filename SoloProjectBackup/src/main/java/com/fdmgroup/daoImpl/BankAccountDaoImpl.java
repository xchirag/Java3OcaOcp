package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.Dao.BankAccountDao;
import com.fdmgroup.entity.BankAccount;
import com.fdmgroup.entity.Customer;

public class BankAccountDaoImpl implements BankAccountDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public void setupEnvironment() {

		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

//	@Override
	public void addBankAccount(BankAccount bankAccount) {

		//********** add get and set method for bankId
		//********** add constructor with bankId in bankAccount class along with above
		
		setupEnvironment();
		BankAccount bAccount = entityManager.find(BankAccount.class, bankAccount.getBankId());

		if (bAccount == null) {
			transaction.begin();
			entityManager.merge(bankAccount);
			transaction.commit();
		}
	}

//	@Override
	public BankAccount getBankAccount(int bankId) {

		setupEnvironment();
		BankAccount bankAccount = entityManager.find(BankAccount.class, bankId);
		return bankAccount;
	}

//	@Override
	public void removeBankAccount(int bankId) {

		setupEnvironment();
		BankAccount bAccount = entityManager.find(BankAccount.class, bankId);
		if (bAccount != null) {
			transaction.begin();
			entityManager.remove(bAccount);
			transaction.commit();
		}
	}

	//@Override
	public void updateBankAccount(BankAccount bankAccount) {

		setupEnvironment();
		BankAccount bAccount = entityManager.find(BankAccount.class, bankAccount.getBankId());
		if (bAccount != null) {

			transaction.begin();
			entityManager.merge(bankAccount);
			transaction.commit();
		}

	}

	//@Override
	public List<BankAccount> listOfBanks() {
		setupEnvironment();
		List<BankAccount> resultList = entityManager.createQuery("select a from ng_bankaccount a", BankAccount.class).getResultList();
		return resultList;
	}
	
	public BankAccount getBankAccountByCustomerNumber(int customerNumber){
		
		/*setupEnvironment(); 
		TypedQuery<Customer> query = entityManager.createQuery("select n from ng_customer n where n.userName = :name", Customer.class);
		return query.setParameter("name", username).getResultList();*/
		
		setupEnvironment();
		//BankAccount bankAccount = entityManager.createQuery("select )
		TypedQuery<BankAccount> query = entityManager.createQuery("select n from ng_bankaccount n where n.customer.customerNumber = :custNum", BankAccount.class);
		//entityManager.createQuery("select bankId, accountBalance, CustomerNumber from ng_bankaccount where CustomerNumber = :custNum", BankAccount.class);
		
		/*query.setParameter("custNum", customerNumber);
		BankAccount bankSingleResult = query.getSingleResult(); */
		
		// above is not safe to do because there is a possibility that the where clause may generate more than one result when 
		// this query would fail better way to get ResultSet of query and then verify size is one and then pass it back as under
		
		query.setParameter("custNum", customerNumber);
		List<BankAccount> bankResultList = query.getResultList();
		if (bankResultList.size() != 0)
			return bankResultList.get(0);
		else
			return null;
	
	}

}

/*package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fdmgroup.Dao.BankAccountDao;
import com.fdmgroup.entity.BankAccount;

public class BankAccountDaoImpl implements BankAccountDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public void setupEnvironment() {

		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

	@Override
	public void addBankAccount(BankAccount bankAccount) {

		//********** add get and set method for bankId
		//********** add constructor with bankId in bankAccount class along with above
		
		setupEnvironment();
		BankAccount bAccount = entityManager.find(BankAccount.class, bankAccount.getBankId());

		if (bAccount == null) {
			transaction.begin();
			entityManager.merge(bankAccount);
			transaction.commit();
		}
	}

	@Override
	public BankAccount getBankAccount(int bankId) {

		setupEnvironment();
		BankAccount bankAccount = entityManager.find(BankAccount.class, bankId);
		return bankAccount;
	}

	@Override
	public void removeBankAccount(int bankId) {

		setupEnvironment();
		BankAccount bAccount = entityManager.find(BankAccount.class, bankId);
		if (bAccount != null) {
			transaction.begin();
			entityManager.remove(bAccount);
			transaction.commit();
		}
	}

	@Override
	public void updateBankAccount(BankAccount bankAccount) {

		setupEnvironment();
		BankAccount bAccount = entityManager.find(BankAccount.class, bankAccount.getBankId());
		if (bAccount != null) {

			transaction.begin();
			entityManager.merge(bankAccount);
			transaction.commit();
		}

	}

	@Override
	public List<BankAccount> listOfBanks() {
		setupEnvironment();
		List<BankAccount> resultList = entityManager.createQuery("select a from ng_bankaccount a", BankAccount.class).getResultList();
		return resultList;
	}

}
*/