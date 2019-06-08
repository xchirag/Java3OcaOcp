package com.fdmgroup.currency.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fdmgroup.currency.dao.CurrencyCoverterDaoImpl;
import com.fdmgroup.currency.model.Currency;
import com.fdmgroup.currency.model.CurrencyList;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterDaoImplTest {

	private CurrencyCoverterDaoImpl dao = new CurrencyCoverterDaoImpl();

	@InjectMocks
	private CurrencyCoverterDaoImpl mocKdao;

	@Mock
	List<Currency> mockCurrencyList;
	
	@Mock
	CurrencyList mockCurrency;
	
	@Mock
	Currency c;

	@Test
	public void Test() {
		
		String ss = "USD";
		double d = 2.55d;
		
		when(mocKdao.returnConversionRate(mockCurrency, ss)).thenReturn(d);
		
		assertEquals(2.55, mocKdao.returnConversionRate(mockCurrency, ss), 0.001);

		//when(target.getAllLions().size()).thenReturn(2);
		//when(mocKdao.grabXML(mockCurrency).

//		//mocKdao.grabXML(mockCurrency);
//		//Currency c = new Currency(1.0d, "USD");
//		
//		when(mocKdao.returnConversionRate(mockCurrency, "USD")).thenReturn(0.01d);
//		
//		verify(mocKdao).returnConversionRate(mockCurrency,"USD");
		
		//assertEquals(1.89, mocKdao.returnConversionRate(mockCurrency, "USD"), 0.001);
		
		//verify(mockCurrencyList, times(1)).add(c);
		
		//when(mocKdao.grabXML(mockCurrency)).
		
	}

//	@Test
//	public void testConversionRateReturned_USD() {
//
//		CurrencyList cl = new CurrencyList();
//		dao.grabXML(cl);
//		double expected = 1.1879;
//		double actual = dao.returnConversionRate(cl, "USD");
//		assertEquals(expected, actual, 0.1);
//	}
//
//	@Test
//	public void testGrabXMLHasPopulatedList() {
//		CurrencyList cl = new CurrencyList();
//		int initial = cl.getCurrencyList().size();
//		dao.grabXML(cl);
//		int end = cl.getCurrencyList().size();
//		assertTrue(end > initial);
//	}
//
//	@Test
//	public void testConversionRateReturned_currencyNotFound() {
//		CurrencyList cl = new CurrencyList();
//		double expected = 0;
//		double actual = dao.returnConversionRate(cl, "doesn't exist");
//		assertEquals(expected, actual, 0.01);
//
//	}

}
