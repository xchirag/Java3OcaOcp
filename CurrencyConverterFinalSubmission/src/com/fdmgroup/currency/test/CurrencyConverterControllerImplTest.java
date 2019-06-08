package com.fdmgroup.currency.test;

import static org.junit.Assert.*;
import org.junit.Assert;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fdmgroup.currency.controller.CurrencyConverterControllerImpl;
import com.fdmgroup.currency.dao.CurrencyConverterDao;
import com.fdmgroup.currency.model.Currency;
import com.fdmgroup.currency.model.CurrencyList;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterControllerImplTest {

	// private CurrencyConverterControllerImpl cur = new
	// CurrencyConverterControllerImpl();
	// private CurrencyList cl = new CurrencyList();

	@InjectMocks
	CurrencyConverterControllerImpl cur;

	@Mock
	CurrencyList cl;

	@Mock
	CurrencyConverterControllerImpl mockCurConConImpl;

	@Mock
	CurrencyConverterDao mockDao;

	@Mock
	CurrencyList mockCurrencyList;

	// @Test
	public void testConvertFromEuro_returnsCorrectResult() {

		double actual = cur.convertFromEuro(cl, "USD", 100);
		double expected = 100 * 1.1879;
		assertEquals(actual, expected, 0.1);

	}

	@Test
	public void testConvertToEuro_returnsCorrectResult() {

		double actual = cur.convertToEuro(cl, "USD", 100);
		double expected = 100 / 1.1879;
		assertEquals(actual, expected, 0.1);

	}

	//@Test
	public void testReturnCurrenciesList_containsUSD() { // test will not run if
															// internet is off!
		// act
		mockDao.grabXML(mockCurrencyList);
		when(cur.returnCurrenciesList(mockCurrencyList).get(0)).thenReturn("USD");
		assertEquals("USD", cur.returnCurrenciesList(mockCurrencyList).get(0));
	}
	
	@Test
	public void test_returnCurrenciesList_callsGrabXMLMethodofDao (){ ///only this one works!
		
		cur.returnCurrenciesList(mockCurrencyList);
		verify(mockDao, times(1)).grabXML(mockCurrencyList);
		
	}

	@Test
	public void test_returnCurrenciesList_returnsOneSizeListwhenXMLfileContainsOnlyOneCurrency(){
		
		List<String> mock2 = new ArrayList<>();
		mock2.add("USD");
		mock2.add("Euro");
		when(cur.returnCurrenciesList(mockCurrencyList)).thenReturn(mock2);
		//assertEquals(1, cur.returnCurrenciesList(mockCurrencyList).size());
		//verify(mockDao, times(1)).grabXML(mockCurrencyList);
		
		assertEquals(2, mock2.size());
		verify(mockDao, times(1)).grabXML(mockCurrencyList);
		
	}

}


/*
 * 
 * //		// Create list
//		List<Currency> curList = new ArrayList<>();
//		
//		List<String> result = cur.returnCurrenciesList(cl);
//		// when(mockDao.returnCurrenciesList(curList)).
//
//		when(mockDao.returnCurrenciesList(cl).get(0)).thenReturn("USD");
//
//		boolean flag = false;
//
//		for (String currency : result) {
//			if (currency.equals("USD")) {
//				flag = true;
//			}
//		}
//		assertTrue(flag);
//	}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
