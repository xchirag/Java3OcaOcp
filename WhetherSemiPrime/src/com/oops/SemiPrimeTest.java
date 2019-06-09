package com.oops;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.*;

public class SemiPrimeTest {
	
	private SemiPrime target = null;
	boolean result = false;
	
	@Before
	public void setup(){
		target = new SemiPrime();
		
	}
	
	@Test
	public void whenPassedWithSemiPrimeNumberItReturnsTrue() {
		boolean result = target.semiprime(91);
		assertEquals(true, result);
	}
	
	@Test
	public void whenPassedWithZeroReturnedResultShouldBeFalse() {
		boolean result = target.semiprime(0);
		assertEquals(false, result);
		
	}
	
	@Test
	public void whenPassedWithNegativeNumberResultShouldBeFalse() {
		result = target.semiprime(-91);
		assertEquals(false, result);
	}
	
	@Test
	public void fewOtherSemiPrimeNumbersTestedToReturnTrue() {
		result = target.semiprime(55);
		assertEquals(true, result);
		result = target.semiprime(74);
		assertEquals(true, result);
	}
	
	@Test
	public void fewOtherNonSemiPrimeNumbersTestedToReturnFalse() {
		result = target.semiprime(64);
		assertEquals(false, result);
		result = target.semiprime(89);
		assertEquals(false, result);
	}
}
