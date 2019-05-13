package cn.saosao.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.saosao.pojo.Claim_List;
import cn.saosao.pojo.Coverage;
import cn.saosao.pojo.Users;
import cn.saosao.service.IClaimListService;
import cn.saosao.service.IClerkService;
import cn.saosao.service.ICoverageService;
import cn.saosao.service.IPolicyService;
import cn.saosao.service.IStatusService;
import cn.saosao.service.IUsersService;

@Controller
public class AllListController {
	@Autowired
	IClaimListService iClaimListService;
	@Autowired
	IClerkService iClerkService;
	@Autowired 
	IPolicyService iPolicyService;
	@Autowired
	IStatusService iStatusService;
	@Autowired
	IUsersService iUsersService;
	@Autowired
	ICoverageService iCoverageservice;
	
	@RequestMapping("/getall")
	public String allList(Model model,Claim_List list) {
		
		Map map=new HashMap();
		Integer cp=1;
		Integer ps=5;
		map.put("ps", ps);
		map.put("cp",cp);
		iClaimListService.getAll(map);
		List<Claim_List> lista = (List)map.get("claim_list");
		BigDecimal line_count=(BigDecimal) map.get("v_count");
		BigDecimal totlepage=(BigDecimal)map.get("totlepage");
		model.addAttribute("claim_list", lista);
		model.addAttribute("line_count",line_count);
		model.addAttribute("totlepage",totlepage);
		for (Claim_List claim_List : lista) {
			System.out.println(claim_List);
		}
		return "backPage/referAll";
	}
	
	@ResponseBody
	@GetMapping("/getcov")
	public Coverage getcover() {
		Coverage coverageById = iCoverageservice.getCoverageById("101");
		System.out.println(coverageById);
		return coverageById;
	}

}
