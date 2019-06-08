package com.fdmgroup.currency.controller;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.currency.dao.CurrencyConverterDao;
import com.fdmgroup.currency.dao.CurrencyCoverterDaoImpl;
import com.fdmgroup.currency.model.Currency;
import com.fdmgroup.currency.model.CurrencyList;

public class CurrencyConverterControllerImpl implements CurrencyConverterController{
	
	private CurrencyConverterDao dao;
	
	public CurrencyConverterControllerImpl() {
		this.dao = new CurrencyCoverterDaoImpl();
	}

	@Override
	public double convertFromEuro(CurrencyList list, String userCurrency, double userAmount) {
		dao.grabXML(list);
		 
		 double currencyRate = dao.returnConversionRate(list, userCurrency);
		 
		 return userAmount * currencyRate;
	}

	@Override
	public double convertToEuro(CurrencyList list, String userCurrency, double userAmount) {
		dao.grabXML(list);
		 
		double currencyRate = dao.returnConversionRate(list, userCurrency);
		 
		 return userAmount / currencyRate;
	}

	@Override
	//
	public List<String> returnCurrenciesList(CurrencyList list) {
		dao.grabXML(list);
		List<String> output = new ArrayList<String>();
		for(int x = 0; x < list.getCurrencyList().size(); x++){
		output.add(list.getCurrencyList().get(x).getCurrencyName());
		}
		return output;
		
	}
}
