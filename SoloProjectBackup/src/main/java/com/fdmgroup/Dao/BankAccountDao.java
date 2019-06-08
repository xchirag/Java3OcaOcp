package com.fdmgroup.Dao;
import java.util.List;

import com.fdmgroup.entity.BankAccount;

public interface BankAccountDao {

	public void addBankAccount(BankAccount bankAccount);
	public BankAccount getBankAccount(int bankId);
	public void removeBankAccount(int bankId);
	public void updateBankAccount(BankAccount bankAccount);
	public List<BankAccount> listOfBanks();

}
