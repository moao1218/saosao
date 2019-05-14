package cn.saosao.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.saosao.pojo.Claim_List;
import cn.saosao.pojo.Coverage;
import cn.saosao.pojo.Status;
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
	public String allList(Model model,Claim_List claim,String cp,String start_time,String end_time,String timegap) {
		System.out.println(start_time);
		System.out.println(end_time);
		model.addAttribute("claim",claim);
		model.addAttribute("start_time",start_time);
		model.addAttribute("end_time",end_time);
		model.addAttribute("timegap",timegap);
		
		Map map=new HashMap();
		Integer ccp;
		String mintime=null;
		String maxtime=null;
		if(cp==null||cp.equals("")) {
			ccp=1;
		}else {
			ccp=Integer.parseInt(cp);
		}
		Integer ps=5;
		if(timegap!=null&&!timegap.equals("")) {
			String[] split = timegap.split("-");
			mintime=split[0];
			maxtime=split[1];
		}
		map.put("start_time",start_time);
		map.put("end_time", end_time);
		map.put("mintime", mintime);
		map.put("maxtime",maxtime);
		map.put("claim_name", claim.getClaim_name());
		map.put("claimid", claim.getClaimid());
		System.out.println(claim.getPol_property());
		map.put("pol_property", claim.getPol_property());
		if(claim!=null&&claim.getStatus()!=null) {
			map.put("statusid", claim.getStatus().getStatusid());
			model.addAttribute("statusid",claim.getStatus().getStatusid());
		}
		map.put("ps", ps);
		map.put("cp",ccp);
		
		iClaimListService.getAll(map);
		List<Status> allStatus = iStatusService.findAllStatus();
		List<Claim_List> lista = (List)map.get("claim_list");
		BigDecimal line_count=(BigDecimal) map.get("v_count");
		BigDecimal totlepage=(BigDecimal)map.get("totlepage");
		System.out.println(line_count);
		model.addAttribute("cp",ccp);
		model.addAttribute("claim_list", lista);
		model.addAttribute("line_count",line_count);
		model.addAttribute("totalpage",totlepage);
		model.addAttribute("allStatus",allStatus);
		System.out.println("totalpage"+totlepage);
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
