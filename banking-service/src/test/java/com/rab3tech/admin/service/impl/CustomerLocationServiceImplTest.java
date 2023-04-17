package com.rab3tech.admin.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import com.rab3tech.customer.dao.repository.CustomerLocationRepository;
import com.rab3tech.dao.entity.Location;
import com.rab3tech.dao.entity.Login;
import com.rab3tech.vo.LocationVO;

public class CustomerLocationServiceImplTest {
	
	@Mock
	private CustomerLocationRepository customerLocationRepository;
	
	@InjectMocks
	private CustomerLocationServiceImpl customerLocationServiceImpl;
	
	@Before
	public void init() {
		 MockitoAnnotations.initMocks(this);
	}
	
	public void update(LocationVO locationVO) {
		Location location = customerLocationRepository.findById(locationVO.getId()).get();
		location.setLcode(locationVO.getLcode());
		//location.setName(locationVO.getName());
		//location.setDom(new Timestamp(new Date().getTime()));
	}
	
	@Test	
	public void testUpdate() {
		LocationVO locationVO = new LocationVO();
		locationVO.setLcode("L292");
		locationVO.setName("INDIA");
		
		Location location=new Location();
		BeanUtils.copyProperties(locationVO, location);
		when(customerLocationRepository.findById(anyInt())).thenReturn(Optional.of(location));
		
		customerLocationServiceImpl.update(locationVO);
		
		verify(customerLocationRepository, times(1)).findById(anyInt());
		verifyNoMoreInteractions(customerLocationRepository);
	}
	
	@Test	
	public void testSave() {
		
		LocationVO locationVO = new LocationVO();
		locationVO.setLcode("L292");
		locationVO.setName("INDIA");
		
		Location location=new Location();
		BeanUtils.copyProperties(locationVO, location);
		when(customerLocationRepository.save(any())).thenReturn(location);
		
		String result=customerLocationServiceImpl.save(locationVO);
		Assert.assertEquals("success",result);
		
		verify(customerLocationRepository, times(1)).save(any());
		verifyNoMoreInteractions(customerLocationRepository);
	}
	
	
	@Test	
	public void testFindByIdWhenLocationExist() {
    	int locationId=444;
    	Location location = new Location();
    	location.setId(locationId);
    	location.setLcode("L010");
    	location.setLocation("India");
    	//Optional.of(location) ->>> Optional<Location>
    	//This mocking 
    	when(customerLocationRepository.findById(locationId)).thenReturn(Optional.of(location));
    	Optional<LocationVO> optional=customerLocationServiceImpl.findById(locationId);
    	LocationVO locationVO=optional.get();
    	Assert.assertEquals(true, optional.isPresent());
    	Assert.assertEquals("India",locationVO.getName());
    	Assert.assertEquals("L010",locationVO.getLcode());
	}
	
    @Test
    public void testFindByIdWhenLocationNotExist(){
    	int locationId=444;
    	//Optional.of(location) ->>> Optional<Location>
    	//This mocking 
    	when(customerLocationRepository.findById(locationId)).thenReturn(Optional.empty());
    	Optional<LocationVO> optional=customerLocationServiceImpl.findById(locationId);
    	assertEquals(false, optional.isPresent());
	}
	
	
	@Test
	public void testFindLocationWhenItExist() {
		List<Location> locationList = new ArrayList<Location>();
		
		Location location = new Location();
		location.setId(100);
		location.setLcode("L010");
		location.setLocation("India");
		
		Login login=new Login();
		login.setEmail("9292@gmail.com");
		login.setLoginid("ame88@gmail.com");
		login.setName("Nagendra");
		login.setNoOfAttempt(12);
		login.setPassword("324");
		location.setLogin(login);
		
		locationList.add(location);
		
		when(customerLocationRepository.findAll()).thenReturn(locationList);
		
		
		List<LocationVO> locationVOs = customerLocationServiceImpl.findLocation();
		assertNotNull(locationVOs);
		assertEquals(1, locationVOs.size());
		assertEquals("L010",locationVOs.get(0).getLcode());
		assertEquals("India",locationVOs.get(0).getName());

		verify(customerLocationRepository, times(1)).findAll();
		verifyNoMoreInteractions(customerLocationRepository);
	}
	
	@Test
	public void testFindLocationWhenItNotExist() {
		List<Location> locations = new ArrayList<Location>();
		when(customerLocationRepository.findAll()).thenReturn(locations);
		List<LocationVO> locationVOs = customerLocationServiceImpl.findLocation();
		assertEquals(0, locationVOs.size());
		verify(customerLocationRepository, times(1)).findAll();
		verifyNoMoreInteractions(customerLocationRepository);
	}

}
