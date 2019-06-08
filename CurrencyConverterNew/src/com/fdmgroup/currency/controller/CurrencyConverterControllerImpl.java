package com.fdmgroup.currency.controller;

import com.fdmgroup.currency.dao.CurrencyConverterDao;
import com.fdmgroup.currency.dao.CurrencyCoverterDaoImpl;

public class CurrencyConverterControllerImpl implements CurrencyConverterController{
	
	private CurrencyConverterDao dao;
	
	public CurrencyConverterControllerImpl() {
		this.dao = new CurrencyCoverterDaoImpl();
	}

	@Override
	public double convertFromEuro(String userCurrency, double userAmount) {
		 
		 double currencyRate = dao.readRate(userCurrency);
		 
		 return userAmount * currencyRate;
	}

	@Override
	public double convertToEuro(String userCurrency, double userAmount) {
		 
		 double currencyRate = dao.readRate(userCurrency);
		 
		 return userAmount / currencyRate;
	}

}
