package com.rab3tech.common.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rab3tech.customer.dao.repository.BranchRepository;
import com.rab3tech.dao.entity.Branch;
import com.rab3tech.vo.BranchVO;

public class BranchServiceImplTest {
	
	@Mock
	private BranchRepository mochBranchRepository;
	
	@InjectMocks
	private BranchServiceImpl branchServiceImpl;
	
	@Before
	public void init() {
		 MockitoAnnotations.initMocks(this); //Initializing mocking for each test cases
	}
	
	@Test
	public void testSave() {
		
		BranchVO branchVO=new BranchVO();
		branchVO.setAddress("JA82");
		branchVO.setCity("Fremont");
		branchVO.setCode("O92192");
		branchVO.setIfsc("ICICI91828");
		
		Branch branch=new Branch();
		branch.setAddress("JA82");
		branch.setCity("Fremont");
		branch.setCode("O92192");
		branch.setIfsc("ICICI91828");
		
		Branch dbranch=new Branch();
		dbranch.setAddress("JA82");
		dbranch.setCity("Fremont");
		dbranch.setCode("O92192");
		dbranch.setIfsc("ICICI91828");
		dbranch.setId(100);
		
		//stubbing
		when(mochBranchRepository.save(isA(Branch.class)))
		.thenReturn(dbranch);
		
		BranchVO result=branchServiceImpl.save(branchVO);
		assertNotNull(result);
		assertEquals(100, result.getId());
		assertEquals("JA82", result.getAddress());
		assertEquals("Fremont", result.getCity());
		assertEquals("O92192", result.getCode());
		assertEquals("ICICI91828", result.getIfsc());
		
	}

	@Test
	public void testDeleteById() {
		
		//mocking the method which does return anything
		doNothing().when(mochBranchRepository).deleteById(isA(Integer.class));
		
		branchServiceImpl.deleteById(100);
		verify(mochBranchRepository, times(1)).deleteById(isA(Integer.class));
		verifyNoMoreInteractions(mochBranchRepository);
	}
	
	
	@Test(expected=NoSuchElementException.class)
	public void testFindByIdWhenNotExit() {
		when(mochBranchRepository.findById(100))
		.thenReturn(Optional.empty());
	   branchServiceImpl.findById(100);
	}

	@Test
	public void testFindByIdWhenExit() {
		
		Branch branch=new Branch();
		branch.setAddress("JA82");
		branch.setCity("Fremont");
		branch.setCode("O92192");
		branch.setIfsc("ICICI91828");
		
		when(mochBranchRepository.findById(100))
		.thenReturn(Optional.of(branch));
		
		BranchVO branchVO=branchServiceImpl.findById(100);
		assertNotNull(branchVO);
		assertEquals("JA82", branchVO.getAddress());
		assertEquals("Fremont", branchVO.getCity());
		assertEquals("O92192", branchVO.getCode());
		assertEquals("ICICI91828", branchVO.getIfsc());
	}

	
	
}
