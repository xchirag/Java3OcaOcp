package com.fdmgroup.currency.controller;

import java.util.List;

import com.fdmgroup.currency.model.CurrencyList;

public interface CurrencyConverterController {

	public double convertFromEuro(CurrencyList list, String userCurrency, double userAmount);

	public List<String> returnCurrenciesList(CurrencyList list);

	public double convertToEuro(CurrencyList list, String userCurrency, double userAmount);
}
