package com.rab3tech.admin.ui.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.common.service.BranchService;
import com.rab3tech.vo.BranchVO;


// th:href="@{/admin/addBranch}
@Controller
@RequestMapping("/admin")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@GetMapping("/addBranch")
	public String addBranch(Model model){
		BranchVO branchVO=new BranchVO();
		model.addAttribute("branchVO", branchVO);
		return "admin/addBranch";
	}
	
	//@ModelAttribute ->> map all the coming from form data into Java object
	@PostMapping("/addBranch")
	public String addBranchPost(@ModelAttribute BranchVO branchVO){
		branchVO.setDoe(new Date());
		branchVO.setDom(new Date());
		branchVO.setPin("03393");
		branchService.save(branchVO);
		return "redirect:/bank/showBranch";
	}
	
	//@ModelAttribute ->> map all the coming from form data into Java object
		@GetMapping("/deleteBranch")
		public String deleteBranchPost(@RequestParam("name") String name){
			branchService.deleteByName(name);
			return "redirect:/bank/showBranch";
		}
	
	
}
