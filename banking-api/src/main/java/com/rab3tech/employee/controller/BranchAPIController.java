package com.rab3tech.employee.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rab3tech.common.service.BranchService;
import com.rab3tech.vo.ApplicationResponseVO;
import com.rab3tech.vo.BranchVO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v3")
public class BranchAPIController {
	
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping("/branches")
	public BranchVO createBranch(@RequestBody BranchVO branchVO){
		branchVO.setDoe(new Date());
		branchVO.setDom(new Date());
		return branchService.save(branchVO);
	}
	
	
	@GetMapping("/branches")
	public List<BranchVO> showBranches(){
		return branchService.findAll();
	}
	
	@DeleteMapping("/branches/{id}")
	public ApplicationResponseVO deleteBranch(@PathVariable int id){
		 ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		 branchService.deleteById(id);
		 applicationResponseVO.setCode(200);
		 applicationResponseVO.setMessage("Branch is deleted successfully");
		 return applicationResponseVO;
	}
	
	@PatchMapping("/branches")
	public ApplicationResponseVO updateBranch(@RequestParam String fieldName,@RequestParam String value,@RequestParam int id){
		 ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		 branchService.partialUpdate(fieldName, value, id);
		 applicationResponseVO.setCode(200);
		 applicationResponseVO.setMessage("Branch is updated successfully");
		 return applicationResponseVO;
	}
	
	@GetMapping("/branches/{id}")
	public BranchVO showBranch(@PathVariable int id){
		return branchService.findById(id);
	}
	
	@GetMapping(value="/branches",params={"ifsc"})
	public BranchVO showBranchByIfsc(@RequestParam String ifsc){
		return branchService.findByIfsc(ifsc);
	}
	
	@GetMapping(value="/branches",params={"code"})
	public BranchVO showBranchByCode(@RequestParam String code){
		return branchService.findByCode(code);
	}
	

}
