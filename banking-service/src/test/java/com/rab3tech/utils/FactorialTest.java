package com.rab3tech.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

//This is my JUnit class
public class FactorialTest {
	
	
	@Before
	public void ahahah(){
		System.out.println(")@)@)@)@");
	}
	
	
	@After
	public void dee(){
		System.out.println(")@)@)@)@");
	}
	
	@Test
	public void testComputeWhenNum3() {
		Factorial factorial=new Factorial(3);
		int result=factorial.compute();
		assertEquals(6, result);		
	}
	
	
	@Test
	public void testComputeWhenNumOne() {
		Factorial factorial=new Factorial(1);
		int result=factorial.compute();
		assertEquals(1, result);		
	}
	
	
	@Test
	public void testComputeWhenNumSeven() {
		Factorial factorial=new Factorial(7);
		int result=factorial.compute();
		assertEquals(5040, result);		
	}
	
	
	
	@Test
	public void testComputeWhenNumZero() {
		Factorial factorial=new Factorial(0);
		int result=factorial.compute();
		assertEquals(1, result);		
	}
	
	
	@Test(expected = ArithmeticException.class)
	public void testComputeWhenNumNegative() {
		Factorial factorial=new Factorial(-4);
		factorial.compute();
	}
	
	@Ignore
	@Test(expected = ArithmeticException.class)
	public void testComputeWhenNumNegative2() {
	}

}
