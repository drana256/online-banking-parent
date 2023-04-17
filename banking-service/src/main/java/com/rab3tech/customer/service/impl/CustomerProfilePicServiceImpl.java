package com.rab3tech.customer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rab3tech.customer.dao.repository.CustomerProfilePicRepository;
import com.rab3tech.customer.dao.repository.LoginRepository;
import com.rab3tech.customer.service.CustomerProfilePicService;
import com.rab3tech.dao.entity.CustomerProfilePicEntity;
import com.rab3tech.dao.entity.Login;

@Service
@Transactional
public class CustomerProfilePicServiceImpl implements CustomerProfilePicService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private CustomerProfilePicRepository customerProfilePicRepository;
	
	
	@Override
	public void save(String username,byte[] photo) {
		//Creating instance of entity
		CustomerProfilePicEntity customerProfilePicEntity=new CustomerProfilePicEntity();
		customerProfilePicEntity.setDoe(new Timestamp(new Date().getTime()));
		customerProfilePicEntity.setDom(new Timestamp(new Date().getTime()));
		customerProfilePicEntity.setPhoto(photo);
		
		//Fetch login entity from database
		Login login=loginRepository.findById(username).get();
		customerProfilePicEntity.setLogin(login);
		
		customerProfilePicRepository.save(customerProfilePicEntity);
	}
	
	@Override
	public List<Integer> findAllPicIds(String username) {
		
		List<Integer>  integers=new ArrayList<Integer>();
		//Fetch login entity from database
		Login login=loginRepository.findById(username).get();
		List<CustomerProfilePicEntity> customerProfilePicEntity=login.getCustomerProfilePics();
		for(CustomerProfilePicEntity entity : customerProfilePicEntity){
			integers.add(entity.getPpid());
		}
		
		return integers;
	}
	
	@Override
	public byte[] findPicById(int ppid) {
		byte[]  photo=customerProfilePicRepository.findById(ppid).get().getPhoto();
		return photo;
	}
}
