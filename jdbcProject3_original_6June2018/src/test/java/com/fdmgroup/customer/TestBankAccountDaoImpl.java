package com.fdmgroup.customer;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.classimp.BankAccount;
import com.fdmgroup.classimp.BankAccountDaoImpl;
import com.fdmgroup.customerDao.Customer;

public class TestBankAccountDaoImpl {

	private Customer john, potter;
	private BankAccount johnCurrentAccount, johnSavingAccount, potterCurrentAccount, potterSavingAccount;

	private BankAccountDaoImpl targetBankAccountDaoImpl;

	@Before
	public void setup() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from XBankAccount").executeUpdate();
		entityManager.createQuery("delete from XCustomer").executeUpdate();
		transaction.commit();

		john = new Customer(1, "John", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com",
				"Birmingham street, Birmingham, B32 9LP");
		potter = new Customer(2, "Potter", "1/4 Street Palace", "harry.magic@gmail.com", "1/4 Street Palace");

		johnCurrentAccount = new BankAccount(john, 600d);
		johnSavingAccount = new BankAccount(john, 1900.50d);
		potterCurrentAccount = new BankAccount(potter, 250d);
		potterSavingAccount = new BankAccount(potter, 420d);

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
		assertEquals(johnSavingAccount, targetBankAccountDaoImpl.listOfBanks().get(0));
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
		String actual = targetBankAccountDaoImpl.getBankAccount(2).getCustomer().getCustomerName();
		assertEquals(expected, actual);
	}

	@Test
	public void test_getAccountBalanceMethodOfReturnedCustomerMatchesWithTheirActualAccountBalance() {

		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		double expected = johnCurrentAccount.getAccountBalance();
		double actual = targetBankAccountDaoImpl.getBankAccount(0).getAccountBalance();

		assertEquals(expected, actual, 0.001);
	}

	@Test
	public void test_removeBankAccountRemovesBankAccountFromTheList_AndReducesSizeOfReturnedListOfBanksByOne() {

		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.removeBankAccount(1);
		assertEquals(1, targetBankAccountDaoImpl.listOfBanks().size());

	}

	@Test
	public void test_ifRemoveBankAccountHasCalledTwiceThenTheReturnedListOfBanksHasSizeDecreasedByTwo() {
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.removeBankAccount(1);
		targetBankAccountDaoImpl.removeBankAccount(2);
		assertEquals(2, targetBankAccountDaoImpl.listOfBanks().size());
	}

	@Test
	public void test_updateBankAccountUpdatesTheAccountBalanceOfCustomerAsPerModifications() {

		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		BankAccount johnUpdatedSavingAccount = new BankAccount(john, 890d);
		targetBankAccountDaoImpl.updateBankAccount(johnUpdatedSavingAccount);

		double expected = johnUpdatedSavingAccount.getAccountBalance();
		double actual = targetBankAccountDaoImpl.getBankAccount(0).getAccountBalance();

		assertEquals(expected, actual, 0.001);

	}
	
	@Test
	public void test_updateBankAccountCanTakeReducedBalanceForSpecificBankAccountAndMakesUpdateAccordingly(){
		
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		BankAccount potterUpdatedCurrentAccount = new BankAccount(potter, 2.50d);
		targetBankAccountDaoImpl.updateBankAccount(potterUpdatedCurrentAccount);
		
		double expected = potterUpdatedCurrentAccount.getAccountBalance();
		double actual = targetBankAccountDaoImpl.getBankAccount(0).getAccountBalance();
		assertEquals(expected, actual, 0.001);
		
	}
	
	@Test
	public void test_updateBankAccountupdatesTheSpecificBankWhenTheBankExistsIntheList_andDoesNotCreateAnyMoreRecords(){
		
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		
		BankAccount johnUpdatedCurrentAccount = new BankAccount(john, 786.86d);
		targetBankAccountDaoImpl.updateBankAccount(johnUpdatedCurrentAccount);
		
		assertEquals(2, targetBankAccountDaoImpl.listOfBanks().size());
	}


	@Test
	public void test_updateBankAccountUpdatesTheBankRecordEvenWhenMoreThanOneAttributesHaveMmodified(){
		
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		
		potterCurrentAccount.setAccountBalance(0.0);
		potterCurrentAccount.setCustomer(new Customer(4, "Poter", "Now Not Living at Old Address", "sameasOld@gmail.com", "Now Not Living at Old Address"));
		
		targetBankAccountDaoImpl.updateBankAccount(potterCurrentAccount);
		
		assertEquals(potterCurrentAccount, targetBankAccountDaoImpl.getBankAccount(1));
	}
	@Test
	public void test_afterRemovingLastBankFromTheList_theListOfBanksReturnsEmptyList(){
		
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.removeBankAccount(0);
		
		assertEquals(0, targetBankAccountDaoImpl.listOfBanks().size());
		
	}


	@Test
	public void test_removeBankRemovesASpecificBankFromTheListWithoutModifyingAnyOtherRecords(){
		
		targetBankAccountDaoImpl.addBankAccount(johnCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(johnSavingAccount);
		
		targetBankAccountDaoImpl.removeBankAccount(2);
		
		assertEquals(johnCurrentAccount, targetBankAccountDaoImpl.listOfBanks().get(0));
		//this requires hashCode and equals in BankAccount
		
	}
	
	//@Test
	//public void test_listOFCusotmerAcceptNewCustomerAfterRemovingCustomerFromTheList()
	
	@Test
	public void test_listOfBankAcceptNewBankAfterRemovingBankFromTheList(){
		
		targetBankAccountDaoImpl.addBankAccount(potterCurrentAccount);
		targetBankAccountDaoImpl.addBankAccount(potterSavingAccount);
		
		targetBankAccountDaoImpl.removeBankAccount(1);
		
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
	

}


// remember default behaviour is that removing an entity from database would not cascade in relation
// therefore as Nick has mentioned we do not need to worry about that part in testing
// if we want to setup such behaviour then implement such in coding phase which will reuqire to update tests accordingly