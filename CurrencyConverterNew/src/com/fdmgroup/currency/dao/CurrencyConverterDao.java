package com.fdmgroup.currency.dao;

import org.w3c.dom.NodeList;

public interface CurrencyConverterDao {
	
	public NodeList grabXML();
	
	public double readRate(String currency);
}
