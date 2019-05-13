package cn.saosao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cn.saosao.pojo.Claim_List;
import cn.saosao.service.IClaimListService;
import cn.saosao.service.IClerkService;
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
	
	@GetMapping("/getall")
	public String allList() {
		return "backPage/referAll";
	}
	@PostMapping("/getall")
	public String condition(Model model,Claim_List list) {
		Map map=new HashMap();
		Integer cp=1;
		Integer ps=5;
		map.put("ps", ps);
		map.put("cp",cp);
		
		return "backPage/referAll";
	}
}
