package com.rab3tech.customer.ui.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.customer.service.CustomerTransactionService;
import com.rab3tech.customer.service.LoginService;
import com.rab3tech.customer.service.impl.SecurityQuestionService;
import com.rab3tech.vo.ChangePasswordVO;
import com.rab3tech.vo.CustomerTransactionVO;
import com.rab3tech.vo.LoginVO;

/**
 * 
 * @author nagendra
 * This class for customer GUI
 *
 */
@Controller
public class PasswordController {
	
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
	@Autowired
	private LoginService loginService;
	
	
	@Autowired
   private CustomerTransactionService customerTransactionService;
	
	
	@GetMapping("/customer/barChart")
	public void createChart(HttpServletResponse response,HttpSession session) throws IOException{
		
		LoginVO  loginVO2=(LoginVO)session.getAttribute("userSessionVO");
		String currentLoggedInUserName=loginVO2.getUsername();
		
		
		//This I need for showing all the transaction details of customer again from,
		//database!
		List<CustomerTransactionVO>  customerTransactionVOs=customerTransactionService.findCustomerTransaction(currentLoggedInUserName);
		
		response.setContentType("image/png");
		//Creating data first
		    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		    
		    for(CustomerTransactionVO customerTransactionVO:customerTransactionVOs){
		    	   dataset.setValue(customerTransactionVO.getAmount(), "Transaction Comparasion", customerTransactionVO.getTxid()+"");
		    }
	     
	       /* dataset.setValue(38, "Gold medals", "China");
	        dataset.setValue(29, "Gold medals", "UK");
	        dataset.setValue(22, "Gold medals", "Russia");
	        dataset.setValue(13, "Gold medals", "South Korea");
	        dataset.setValue(11, "Gold medals", "Germany");*/
	        
	        JFreeChart barChart = ChartFactory.createBarChart(
	                "Transaction Comparasion",
	                "x-axis",
	                "y-axis",
	                dataset,
	                PlotOrientation.VERTICAL,
	                false, true, false);
	        
	        CategoryPlot plot = barChart.getCategoryPlot();
	        plot.getRenderer().setSeriesPaint(0, new Color(128, 0, 0));
	        plot.getRenderer().setSeriesPaint(1, new Color(0, 0, 255));
	        plot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));
	        
	        barChart.getCategoryPlot().setBackgroundPaint(Color.white);
	        barChart.getCategoryPlot().setRangeGridlinePaint(Color.black);
	        
	        ChartUtilities.writeChartAsPNG(response.getOutputStream(), barChart,
					820, 450); 
		
	}
	
	
	@GetMapping("/customer/forget/password")
	public String showForgetPassword(){
		return "/customer/forgetPass";//forgetPass.html
	}
	
	@PostMapping("/customer/forget/password")
	public String showForgetPasswordPost(@RequestParam("email") String email,Model model){
		List<String> questions=securityQuestionService.findQuestionAnswer(email);
		model.addAttribute("questions",questions);
		return "/customer/validateSecurityQuestion";//validateSecurityQuestion.html
	}
	
	@PostMapping("/customer/validate/question")
	public String validateQuestions(@RequestParam("email") String email,
			@RequestParam("securityAns1") String securityAns1,@RequestParam("securityAns2") String securityAns2,Model model){
		boolean status=securityQuestionService.validateQuestionAnswer(email,securityAns1,securityAns2);
		if(status){
			return "/customer/updatePassword";
		}else{
			List<String> questions=securityQuestionService.findQuestionAnswer(email);
			model.addAttribute("questions",questions);
			model.addAttribute("message","Hey! your security questions answer did not match!!!!!!!!!!");
			return "/customer/validateSecurityQuestion";//validateSecurityQuestion.html
		}
	}
	
	
	@PostMapping("/customer/updatePassword")
	public String customerUpdatePassword(@RequestParam("email") String email,
			@RequestParam("newPassword") String newPassword,@RequestParam("confirmPassword") String confirmPassword,Model model){
			if(newPassword!=null && !newPassword.equals(confirmPassword)){
				model.addAttribute("message", "Hey! your new password and confirm password are not same!");
				return "/customer/updatePassword";
			}else{
				//Logic to update password
				ChangePasswordVO changePasswordVO=new ChangePasswordVO();
				changePasswordVO.setLoginid(email);
				changePasswordVO.setNewPassword(newPassword);
				loginService.changePassword(changePasswordVO);
				model.addAttribute("message", "Hey! your password has been changed successfully!");
				return "/customer/login";
			}
	}
	
	

}
