package com.rab3tech.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rab3tech.common.service.BranchService;
import com.rab3tech.customer.dao.repository.BranchRepository;
import com.rab3tech.dao.entity.Branch;
import com.rab3tech.vo.BranchVO;

@Service
//Below annotation for managing transaction by spring container
@Transactional
public class BranchServiceImpl implements BranchService {
	
	@Autowired
	private BranchRepository branchRepository;
	
	
	@Override
	public void partialUpdate(final String fieldName,final String value,final int id) {
		
		//loading Entity class as per database id
		Branch branch=branchRepository.findById(id).get();
		
		switch (fieldName) {
		case "name":
			branch.setName(value);
			break;
		case "code":
			branch.setCode(value);
			break;
		case "address":
			branch.setAddress(value);
			break;
		case "city":
			branch.setCity(value);
		}	
	}
	
	@Override
	public void deleteByName(String  name) {
		branchRepository.deleteByName(name);
	}
	
	@Override
	public void deleteById(int id) {
		branchRepository.deleteById(id);
	}
	
	@Override
	public List<BranchVO> findAll() {
		List<Branch> branchList=branchRepository.findAll();
		List<BranchVO> branchVOs=new ArrayList<BranchVO>();
		for(Branch branch:branchList){
			branchVOs.add(toDTO(branch));
		}
		return branchVOs;
	}
	
	@Override
	public BranchVO save(BranchVO branchVO) {
		Branch entity=new Branch();
		BeanUtils.copyProperties(branchVO, entity);
		Branch dbranch=branchRepository.save(entity);
		branchVO.setId(dbranch.getId());
		return branchVO;
	}
	
	@Override
	public BranchVO findById(int id) {
		Branch dbranch=branchRepository.findById(id).get();
		return toDTO(dbranch);
	}
	
	@Override
	public BranchVO findByCode(String code) {
		Branch dbranch=branchRepository.findByCode(code).get();
		return toDTO(dbranch);
	}
	
	@Override
	public BranchVO findByIfsc(String ifsc) {
		Branch dbranch=branchRepository.findByIfsc(ifsc).get();
		return toDTO(dbranch);
	}
	
	/**
	 * This is common code which will convert Entity class into VO type
	 * @param dbranch
	 * @return
	 */
	private BranchVO toDTO(Branch dbranch){
		BranchVO branchVO=new BranchVO();
		BeanUtils.copyProperties(dbranch, branchVO);
		return branchVO;
	}

}
