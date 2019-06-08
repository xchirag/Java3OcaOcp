package com.fdmgroup.classimp;
import java.util.List;

public interface BankAccountDao {

	public void addBankAccount(BankAccount bankAccount);
	public BankAccount getBankAccount(int bankId);
	public void removeBankAccount(int bankId);
	public void updateBankAccount(BankAccount bankAccount);
	public List<BankAccount> listOfBanks();

}
