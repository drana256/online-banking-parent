package com.rab3tech.common.service;

import java.util.List;

import com.rab3tech.vo.BranchVO;

public interface BranchService {

	public BranchVO save(BranchVO branchVO);

	BranchVO findById(int id);

	BranchVO findByIfsc(String ifsc);

	BranchVO findByCode(String code);

	List<BranchVO> findAll();

	void deleteById(int id);

	void partialUpdate(String fieldName, String value, int id);

	void deleteByName(String name);

}
