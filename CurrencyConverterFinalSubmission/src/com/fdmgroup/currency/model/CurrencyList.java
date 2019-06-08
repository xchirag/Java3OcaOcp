package com.fdmgroup.currency.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList {
	private List<Currency> currencyList;
	
	public CurrencyList(){
		setCurrencyList(new ArrayList<>());
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	public void addCurrencyList(Currency currency) {
		currencyList.add(currency);
		
	}
	
	public List<Currency> viewCurrencyList(){
		return currencyList;
	}
}
