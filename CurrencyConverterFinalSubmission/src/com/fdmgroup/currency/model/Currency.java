package com.fdmgroup.currency.model;

public class Currency {

	private double rate;
	private String currencyName;
	
	public Currency(double rate, String currencyName) {
		super();
		this.setRate(rate);
		this.setCurrencyName(currencyName);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

}
