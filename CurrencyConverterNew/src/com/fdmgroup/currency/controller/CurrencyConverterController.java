package com.fdmgroup.currency.controller;

public interface CurrencyConverterController {

	public double convertFromEuro(String userCurrency, double userAmount);
	
	public double convertToEuro(String userCurrency, double userAmount);
}
