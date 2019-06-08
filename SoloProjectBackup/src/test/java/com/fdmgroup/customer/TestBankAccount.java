package com.fdmgroup.customer;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.BankAccount;
import com.fdmgroup.entity.Customer;

public class TestBankAccount {
	
	
	private Customer john, potter;
	private BankAccount johnCurrentAccount, johnSavingAccount;
	
	
	@Before
	public void setup() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbcProject3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.createQuery("delete from XbankAccount").executeUpdate();
		entityManager.createQuery("delete from XCustomer").executeUpdate();
		transaction.commit();
		
		john = new Customer(1, "John", "password1", "Birmingham street, Birmingham, B32 9LP", "john@yahoo.com", "Birmingham street, Birmingham, B32 9LP", "john7");
		potter = new Customer(2, "Potter", "password2", "1/4 Street Palace", "harry.magic@gmail.com", "1/4 Street Palace", "potter123");
		
		johnCurrentAccount = new BankAccount(12, john, 600d);
		johnSavingAccount = new BankAccount(32, john, 1900.50d);
	}

	//@Test
	public void test_addBalanceMethodIncreasesAccountBalanceAsPerDepositedAmount(){
		
		johnSavingAccount.addBalance(1, 500.0);
		double expected = 500.0 + 600;
		double actual = johnSavingAccount.getAccountBalance();
		assertEquals(expected, actual, 0.001);
		
	}
	
	//@Test
	public void test_withdrawBalanceMethodDecreasesAccountBalanceAsPerWithdrawnAmount(){
		johnSavingAccount.addBalance(1, 500.0);
		johnSavingAccount.withdrawBalance(1, 325.50);
		double expected = 500.0 - 325.5;
		double actual = johnSavingAccount.getAccountBalance();
		assertEquals(expected, actual, 0.001);
	}
	
	
	
}
