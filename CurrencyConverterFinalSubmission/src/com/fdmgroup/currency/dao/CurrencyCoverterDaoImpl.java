package com.fdmgroup.currency.dao;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fdmgroup.currency.model.Currency;
import com.fdmgroup.currency.model.CurrencyList;

public class CurrencyCoverterDaoImpl implements CurrencyConverterDao {

	private NodeList CurrencyNodeList;

	public CurrencyCoverterDaoImpl() {
		CurrencyNodeList = null;
	}

	@Override
	public void grabXML(CurrencyList list) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder
					.parse(new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream());
			CurrencyNodeList = doc.getElementsByTagName("Cube");
			for (int i = 0; i < CurrencyNodeList.getLength(); i++) {
				Element item = (Element) CurrencyNodeList.item(i);
				String rate = item.getAttribute("rate");
				String currency1 = item.getAttribute("currency");
				if(!rate.isEmpty() && !currency1.isEmpty()){
					list.addCurrencyList(new Currency(Double.parseDouble(rate), currency1));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double returnConversionRate(CurrencyList list, String userCurrencyName) {
		for(Currency currency: list.getCurrencyList()){
			if(userCurrencyName.equals(currency.getCurrencyName())){
				return currency.getRate();
			} 
		}
		return 0;
	}

}
