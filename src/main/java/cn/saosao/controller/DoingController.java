package cn.saosao.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.saosao.pojo.Claim_List;
import cn.saosao.pojo.Clerk;
import cn.saosao.pojo.Status;
import cn.saosao.service.IClaimListService;
import cn.saosao.service.IStatusService;

@Controller
public class DoingController {
	@Autowired
	IClaimListService iClaimListService;
	@Autowired
	IStatusService iStatusService;
	@RequestMapping("/doing")
	public String doing(Model model,Claim_List claim,String cp,String start_time,String end_time,String timegap,HttpServletRequest request) throws ParseException {
			HttpSession session = request.getSession();
			Clerk getClerk=(Clerk)session.getAttribute("clerk");
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();

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
			map.put("pol_property", claim.getPol_property());
			if(claim!=null&&claim.getStatus()!=null) {
				map.put("statusid", claim.getStatus().getStatusid());
				model.addAttribute("statusid",claim.getStatus().getStatusid());
			}
			map.put("ps", ps);
			map.put("cp",ccp);
			map.put("idedoing", getClerk.getJob());
			map.put("doinguserid", (getClerk.getMagid()+""));
			iClaimListService.getAll(map);
			List<Status> allStatus = iStatusService.findAllStatus();
			List<Claim_List> lista = (List)map.get("claim_list");
			for (Claim_List claim_List : lista) {
				String claim_date = claim_List.getUpper_date();
				Date parse = sim.parse(claim_date);
				Long emergencytime=(date.getTime()-parse.getTime())/(1000*3600*24);
				if(emergencytime<=50) {
					claim_List.setEmergency("正常");
				}else if(emergencytime<=80){
					claim_List.setEmergency("紧急");
				}else {
					claim_List.setEmergency("非常紧急");
				}
				
			}
			BigDecimal line_count=(BigDecimal) map.get("v_count");
			BigDecimal totlepage=(BigDecimal)map.get("totlepage");
			model.addAttribute("cp",ccp);
			model.addAttribute("claim_list", lista);
			model.addAttribute("line_count",line_count);
			model.addAttribute("totalpage",totlepage);
			model.addAttribute("allStatus",allStatus);

			return "backPage/doing";
	}
}
