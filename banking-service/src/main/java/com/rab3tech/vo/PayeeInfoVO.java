package com.rab3tech.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class PayeeInfoVO {

	private int id;
	private String payeeAccountNo;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String payeeName;
	
	@NotNull
	@Size(min = 5, max = 30)
	private String payeeNickName;
	private String accNumberConfirm;
	private String customerId;
	private Timestamp doe;
	private Timestamp dom;
	private String remarks;
	private String payeeStatus;
	private int urn;
	public Object getAccNumberConfirm() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getPayeeAccountNo() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCustomerId(String username) {
		// TODO Auto-generated method stub
		
	}
	public String getCustomerId() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPayeeStatus(String name) {
		// TODO Auto-generated method stub
		
	}
	public Object getPayeeName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
