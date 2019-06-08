package com.fdmgroup.currency.dao;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CurrencyCoverterDaoImpl implements CurrencyConverterDao {

	private NodeList CurrencyList;

	public CurrencyCoverterDaoImpl() {
		CurrencyList = null;
	}

	@Override
	public double readRate(String currency) {
		try {

			for (int i = 0; i < grabXML().getLength(); i++) {
				Element item = (Element) grabXML().item(i);
				String rate = item.getAttribute("rate");
				String currency1 = item.getAttribute("currency");
				if (currency1.equals(currency)) {
					return Double.parseDouble(rate);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public NodeList grabXML() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder
					.parse(new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream());
			CurrencyList = doc.getElementsByTagName("Cube");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CurrencyList;
	}
}
