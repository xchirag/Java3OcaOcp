package com.fdmgroup.currency.dao;

import com.fdmgroup.currency.model.CurrencyList;

public interface CurrencyConverterDao {
	
	public void grabXML(CurrencyList list);
	
	public double returnConversionRate(CurrencyList list, String userCurrencyName);
}
