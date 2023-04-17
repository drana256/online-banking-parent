package com.test;

public class BizService {
	
	private Utils utils=new Utils();
	
	public String magic(String name){
		String result=utils.genString(name);
		result=result+" THANK YOU";
		return result;
	}

}
