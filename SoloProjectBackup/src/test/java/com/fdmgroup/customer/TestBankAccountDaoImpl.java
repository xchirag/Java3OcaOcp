package com.fdmgroup.customer;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.BankAccount;
import com.fdmgroup.daoImpl.BankAccountDaoImpl;
import com.fdmgroup.entity.Customer;

public class TestBankAccountDaoImpl {

	private Customer john, potter;
	private BankAccount johnCurrentAccount, johnSavingAccount, potterCurrentAccount, potterSavingAccount;

	private BankAccountDaoImpl targetBankAccountDaoImpl;

	@Before
	public void setup() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from ng_bankaccount").executeUpdate();
		entityManager.createQuery("delete from ng_customer").executeUpdate();
		transaction.commit();

		john = new Customer(1, "John", "password1", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com",
				"Birmingham street, Birmingham, B32 9LP", "john123");
		potter = new Customer(2, "Potter", "password2", "1/4 Street Palace", 
				"harry.magic@gmail.com", "1/4 Street Palace", "potter234");

		johnCurrentAccount = new BankAccount(12, john, 600d);
		johnSavingAccount = new BankAccount(22, john, 1900.50d);
		potterCurrentAccount = new BankAccount(32, potter, 250d);
		potterSavingAccount = new BankAccount(42, potter, 420d);

		targetBankAccountDaoImpl = new BankAccountDaoImpl();

	}

	@Test
	public void test_listOfBanksReturnsEmptyListWhenNoBanksAdded() {
		assertTrue(targetBankAccountDaoImpl.listOfBanks().isEmpty());
			
	}

	@Test
	public void test_listOfBanksReturnsListWithSizeOneWhenOneBankAdded() {
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		assertEquals(1, targetBankAccountDaoImpl.listOfBanks().size());
	}

	@Test
	public void test_listOfBanksReturnListWithSameProductAsTheOneAdded() {
		// this require hasEqual methods implemented into BankAccount model
		// class
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		assertEquals(johnSavingAccount, targetBankAccountDaoImpl.getBankAccount(22));
		//assertEquals(johnSavingAccount, targetBankAccountDaoImpl.listOfBanks().get(22));
	}

	@Test
	public void test_listOfBanksReturnListOfSizeTwo_WhenTwoBankAccountsAdded() {

		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		assertEquals(2, targetBankAccountDaoImpl.listOfBanks().size());
	}

	@Test
	public void test_getBankAccountReturnsCorrectBankAccountAsPerIndexProvided() {

		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);

		String expected = potterSavingAccount.getCustomer().getCustomerName();
		String actual = targetBankAccountDaoImpl.getBankAccount(42).getCustomer().getCustomerName();
		assertEquals(expected, actual);
	}

	@Test
	public void test_getAccountBalanceMethodOfReturnedCustomerMatchesWithTheirActualAccountBalance() {

		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		double expected = johnCurrentAccount.getAccountBalance();
		double actual = targetBankAccountDaoImpl.getBankAccount(12).getAccountBalance();

		assertEquals(expected, actual, 0.001);
	}

	@Test
	public void test_removeBankAccountRemovesBankAccountFromTheList_AndReducesSizeOfReturnedListOfBanksByOne() {

		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.removeBankAccount(12);
		assertEquals(1, targetBankAccountDaoImpl.listOfBanks().size());

	}

	@Test
	public void test_ifRemoveBankAccountHasCalledTwiceThenTheReturnedListOfBanksHasSizeDecreasedByTwo() {
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.removeBankAccount(12);
		targetBankAccountDaoImpl.removeBankAccount(32);
		assertEquals(2, targetBankAccountDaoImpl.listOfBanks().size());
	}

	@Test
	public void test_updateBankAccountUpdatesTheAccountBalanceOfCustomerAsPerModifications() {

		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		BankAccount johnUpdatedSavingAccount = new BankAccount(22, john, 890d);
		targetBankAccountDaoImpl.updateBankAccount(johnUpdatedSavingAccount);

		double expected = johnUpdatedSavingAccount.getAccountBalance();
		double actual = targetBankAccountDaoImpl.getBankAccount(22).getAccountBalance();

		assertEquals(expected, actual, 0.001);

	}
	
	@Test
	public void test_updateBankAccountCanTakeReducedBalanceForSpecificBankAccountAndMakesUpdateAccordingly(){
		
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		BankAccount potterUpdatedCurrentAccount = new BankAccount(32, potter, 2.50d);
		targetBankAccountDaoImpl.updateBankAccount(potterUpdatedCurrentAccount);
		
		double expected = potterUpdatedCurrentAccount.getAccountBalance();
		double actual = targetBankAccountDaoImpl.getBankAccount(32).getAccountBalance();
		assertEquals(expected, actual, 0.001);
//		System.out.println(actual);
//		System.out.println(expected);
	}
	
	@Test
	public void test_updateBankAccountupdatesTheSpecificBankWhenTheBankExistsIntheList_andDoesNotCreateAnyMoreRecords(){
		
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		
		BankAccount johnUpdatedCurrentAccount = new BankAccount(12, john, 786.86d);
		targetBankAccountDaoImpl.updateBankAccount(johnUpdatedCurrentAccount);
		
		assertEquals(2, targetBankAccountDaoImpl.listOfBanks().size());
	}


	@Test
	public void test_updateBankAccountUpdatesTheBankRecordEvenWhenMoreThanOneAttributesHaveMmodified(){
		
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		
		potterCurrentAccount.setAccountBalance(0.0);
		potterCurrentAccount.setCustomer(new Customer(32, "Poter", "password3", "Now Not Living at Old Address", 
				"sameasOld@gmail.com", "Now Not Living at Old Address", "poter237"));
		
		targetBankAccountDaoImpl.updateBankAccount(potterCurrentAccount);
		
		assertEquals(potterCurrentAccount, targetBankAccountDaoImpl.getBankAccount(32));
	}
	@Test
	public void test_afterRemovingLastBankFromTheList_theListOfBanksReturnsEmptyList(){
		
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.removeBankAccount(32);
		
		assertEquals(0, targetBankAccountDaoImpl.listOfBanks().size());
		
	}


	@Test
	public void test_removeBankRemovesASpecificBankFromTheListWithoutModifyingAnyOtherRecords(){
		
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		
		targetBankAccountDaoImpl.removeBankAccount(32);
		
		assertEquals(johnCurrentAccount, targetBankAccountDaoImpl.getBankAccount(12));
		//assertEquals(johnCurrentAccount, targetBankAccountDaoImpl.listOfBanks().get(12));
		//this requires hashCode and equals in BankAccount
		
	}
	
	//@Test
	//public void test_listOFCusotmerAcceptNewCustomerAfterRemovingCustomerFromTheList()
	
	@Test
	public void test_listOfBankAcceptNewBankAfterRemovingBankFromTheList(){
		
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		
		targetBankAccountDaoImpl.removeBankAccount(32);
		
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		
		assertEquals(3, targetBankAccountDaoImpl.listOfBanks().size());
	}
	
	@Test
	public void test_removeBankAccountDoesNotRemoveAnyBankAccountFromTheListIFTheIndexProvidedDoesNotExist(){
		
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		
		targetBankAccountDaoImpl.removeBankAccount(5); //does not exist
		
		assertEquals(2, targetBankAccountDaoImpl.listOfBanks().size());
	}
	
	@AfterClass
	public static void clear(){
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from ng_bankaccount").executeUpdate();
		entityManager.createQuery("delete from ng_customer").executeUpdate();
		transaction.commit();
		
	}
	

}


// remember default behaviour is that removing an entity from database would not cascade in relation
// therefore as Nick has mentioned we do not need to worry about that part in testing
// if we want to setup such behaviour then implement such in coding phase which will reuqire to update tests accordingly