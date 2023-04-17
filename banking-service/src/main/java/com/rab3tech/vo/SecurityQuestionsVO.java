package com.rab3tech.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SecurityQuestionsVO {
	
	private int qid;
	private String questions;
	private String status;
	private String owner;
	private Timestamp createdate;
	private Timestamp updatedate;
	public Object getQid() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
}
