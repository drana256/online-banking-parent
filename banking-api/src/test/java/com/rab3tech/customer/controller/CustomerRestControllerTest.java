package com.rab3tech.customer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rab3tech.customer.service.CustomerService;
import com.rab3tech.customer.service.LoginService;
import com.rab3tech.test.TestUtil;
import com.rab3tech.vo.ApplicationResponseVO;
import com.rab3tech.vo.LoginRequestVO;
import com.rab3tech.vo.LoginVO;

public class CustomerRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private LoginService loginService;
	
	@Mock
	private JavaMailSender javaMailSender;
	
	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerRestController customerRestController;
	
	@Before
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		//Initializing mock mvc to hit rest api
		mockMvc = MockMvcBuilders.standaloneSetup(customerRestController)
				// .addFilters(new CorsFilter())
				.build();

	}
	
	@PostMapping("/user/login")
	public ApplicationResponseVO authUser(@RequestBody LoginRequestVO loginRequestVO) {
		Optional<LoginVO>  optional=loginService.findUserByUsername(loginRequestVO.getUsername());
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		if(optional.isPresent()) {
			applicationResponseVO.setCode(200);
			applicationResponseVO.setStatus("success");
			applicationResponseVO.setMessage("Userid is correct");
		}else {
			applicationResponseVO.setCode(400);
			applicationResponseVO.setStatus("fail");
			applicationResponseVO.setMessage("Userid is not correct");
		}
		return applicationResponseVO;
	}
	
	@Test
	public void testAuthUserWhenExist() throws IOException, Exception{
		String username="nage@gmail.com";
		LoginVO loginVO=new LoginVO();
		loginVO.setEmail(username);
		when(loginService.findUserByUsername(username)).thenReturn(Optional.of(loginVO));
		
		LoginRequestVO loginRequestVO=new LoginRequestVO();
		loginRequestVO.setUsername(username);
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/v3/user/login")
				     //what format of data we are sending here
		 	        .contentType(MediaType.APPLICATION_JSON)
		 	         //setting  json data into request body
		 	        .content(TestUtil.convertObjectToJsonBytes(loginRequestVO))
		 	        //What format of data we are expecting from server
		 			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		             //output we are validating here 
		 			.andExpect(jsonPath("$.code").exists())
		 			.andExpect(jsonPath("$.status").exists())
		 			.andExpect(jsonPath("$.message").exists())
		 			.andExpect(jsonPath("$.code").value("200"))
		 			.andExpect(jsonPath("$.status").value("success"))
		 			.andExpect(jsonPath("$.message").value("Userid is correct"))
		 			.andDo(print());
	}
	
	
	@Test
	public void testAuthUserWhenNotExist() throws IOException, Exception{
		
		String username="nage@gmail.com";
		
		when(loginService.findUserByUsername(username)).thenReturn(Optional.empty());
		
		LoginRequestVO loginRequestVO=new LoginRequestVO();
		loginRequestVO.setUsername(username);
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/v3/user/login")
				     //what format of data we are sending here
		 	        .contentType(MediaType.APPLICATION_JSON)
		 	         //setting  json data into request body
		 	        .content(TestUtil.convertObjectToJsonBytes(loginRequestVO))
		 	        //What format of data we are expecting from server
		 			.accept(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk())
		             //output we are validating here 
		 			.andExpect(jsonPath("$.code").exists())
		 			.andExpect(jsonPath("$.status").exists())
		 			.andExpect(jsonPath("$.message").exists())
		 			.andExpect(jsonPath("$.code").value("400"))
		 			.andExpect(jsonPath("$.status").value("fail"))
		 			.andExpect(jsonPath("$.message").value("Userid is not correct"))
		 			.andDo(print());
		
	}

}
