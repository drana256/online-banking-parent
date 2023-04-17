package com.rab3tech.customer.service;

import java.util.List;

public interface CustomerProfilePicService {

	public void save(String username, byte[] photo);

	List<Integer> findAllPicIds(String username);

	byte[] findPicById(int ppid);

}
