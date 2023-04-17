package com.rab3tech.customer.service.impl;

import java.util.List;

import com.rab3tech.vo.CustomerSecurityQueAnsVO;
import com.rab3tech.vo.SecurityQuestionsVO;

public interface SecurityQuestionService {

	List<SecurityQuestionsVO> findAll();

	void save(CustomerSecurityQueAnsVO customerSecurityQueAnsVO);

	List<String> findQuestionAnswer(String email);

	boolean validateQuestionAnswer(String email,String securityAns1,String securityAns2);

}
