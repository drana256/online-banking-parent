package com.rab3tech.customer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rab3tech.customer.dao.repository.CustomerAccountInfoRepository;
import com.rab3tech.customer.dao.repository.CustomerTransactionRepository;
import com.rab3tech.dao.entity.CustomerAccountInfo;
import com.rab3tech.dao.entity.CustomerTransaction;
import com.rab3tech.dao.entity.Login;
import com.rab3tech.vo.CustomerTransactionVO;

//It will execute your test cases and final show the summary of it
//MockitoJUnitRunner ->> this ensures we can utilize all the features of mockito here
@RunWith(MockitoJUnitRunner.class)
public class CustomerTransactionServiceImplTest {
	
	@Mock
	private CustomerTransactionRepository customerTransactionRepository;
	
	@Mock
	private CustomerAccountInfoRepository customerAccountInfoRepository;
	
	@InjectMocks
	private CustomerTransactionServiceImpl customerTransactionServiceImpl;
	
	@Before
	public void initializer(){
		//this keyword always refer current class object 
		MockitoAnnotations.initMocks(this); //Initializing mocking for each test cases
	}
	
	@Test
	public void testFindCustomerTransaction() {
		
		String username="nagen@gmail.com";
		
		CustomerAccountInfo customerAccountInfo=new CustomerAccountInfo();
		customerAccountInfo.setAccountNumber("9383726262");
		customerAccountInfo.setAccountType(null);
		customerAccountInfo.setAvBalance(122);
		customerAccountInfo.setBranch("SBA");
		customerAccountInfo.setCurrency("$");
		customerAccountInfo.setId(100);
		Login ologin=new Login();
		ologin.setLoginid(username);
		customerAccountInfo.setCustomerId(ologin);	
		//Making object Optional
		Optional<CustomerAccountInfo> ocustomerAccountInfo=Optional.of(customerAccountInfo);
		when(customerAccountInfoRepository.findByLoginId(username)).thenReturn(ocustomerAccountInfo);
		
		List<CustomerTransaction> customerTransactions=new ArrayList<>();
		CustomerTransaction customerTransaction=new CustomerTransaction();
		customerTransaction.setAmount(122);
		customerTransaction.setBankName("Aba");
		customerTransaction.setFromAccount("9383726262");
		customerTransaction.setTransactionId("Tx828272");
		customerTransactions.add(customerTransaction);
		
		CustomerTransaction customerTransaction2=new CustomerTransaction();
		customerTransaction2.setAmount(34);
		customerTransaction2.setBankName("Magic Tech");
		customerTransaction2.setFromAccount("9383726262");
		customerTransaction2.setTransactionId("tx723636633");
		customerTransactions.add(customerTransaction2);
		
		when(customerTransactionRepository.findByFromAccount("9383726262")).thenReturn(customerTransactions);
		
		
		//We are writing JUnit for findCustomerTransaction
		List<CustomerTransactionVO> customerTransactionVOs=customerTransactionServiceImpl.findCustomerTransaction(username);
		assertTrue(customerTransactionVOs!=null);
		assertTrue(customerTransactionVOs.size()==2);
		assertEquals("Tx828272", customerTransactionVOs.get(0).getTransactionId());
		assertEquals("Aba", customerTransactionVOs.get(0).getBankName());
		assertEquals("Magic Tech", customerTransactionVOs.get(1).getBankName());
		
	}
	
	

}
