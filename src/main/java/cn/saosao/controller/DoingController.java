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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.saosao.pojo.Claim_List;
import cn.saosao.pojo.Claim_Verify;
import cn.saosao.pojo.Clerk;
import cn.saosao.pojo.Status;
import cn.saosao.service.IClaimListService;
import cn.saosao.service.IClaimVerifyService;
import cn.saosao.service.IItemsListService;
import cn.saosao.service.IStatusService;

@Controller
public class DoingController {
	@Autowired
	IClaimListService iClaimListService;
	@Autowired
	IStatusService iStatusService;
	@Autowired
	IClaimVerifyService iClaimVerifyService;
	@Autowired
	IItemsListService iItemsListService;
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
	@GetMapping("/getclerkdoing/{id}")
	public String getClerkById(Claim_Verify cla_ver,@PathVariable("id") String claimid,Map<String,Object> map,Model model,HttpSession session) {
		Integer cp=1;
		Integer ps=1;
		map.put("ps", ps);
		map.put("cp",cp);
		map.put("claimid",claimid);//"15573813681523542796"
		iClaimListService.getAll(map);
		List<Claim_List> c = (List)map.get("claim_list");
		model.addAttribute("claim", c.get(0));
		Clerk clerk = (Clerk)session.getAttribute("clerk");
		//--------------------------------------------------------------
		System.out.println(clerk);
		if(clerk.getRoleid().equals("10")){
			return "backPage/showdoingInfo";
		}else {
			return "backPage/doingInfo";
		}
	}
	@ResponseBody
	@RequestMapping("/calculate1")
	public Double getCalculate(String claimid) {
		System.out.println("进入了计算");
		List<Claim_Verify> list=iClaimVerifyService.getList(claimid);
		Double price=0.0;
		//
		System.out.println("长度是"+list.size());
		for (Claim_Verify claim_Verify : list) {
			System.out.println(claim_Verify);
			  //发票价格
			  Double invoic=claim_Verify.getInvoice()==null?0:Double.parseDouble(claim_Verify.getInvoice());
			  System.out.println("发票价格"+invoic);
			  //房屋市场价价格 
			  Double housemarket=claim_Verify.getHouse_market()==null?0:Double.parseDouble(claim_Verify.getHouse_market());
			  System.out.println("房屋市场价价格"+housemarket);
			  //使用年限 
			  Double useage=claim_Verify.getUser_age()==null?0:Double.parseDouble(claim_Verify.getUser_age());
			  System.out.println("使用年限"+useage);
			  //免赔率 
			  Double excess = Double.parseDouble(claim_Verify.getItems_list().getExcess());
			  System.out.println("免赔率 "+excess);
			  //折旧率
			  Double dep_rate=Double.parseDouble(claim_Verify.getItems_list().getDep_rate());
			  System.out.println("折旧率 "+dep_rate);
			  if(claim_Verify.getItems_list().getItemid()>=111) {
				  price+=(housemarket-(housemarket*excess)-(dep_rate*useage*housemarket)); 
				  System.out.println("进入了房屋"+price);
			  }else {
				  price+=(invoic-(invoic*excess)-(dep_rate*useage*invoic));
				  System.out.println("进入了武平"+(dep_rate*useage*invoic));
			  }
			 
			
			
		}
		System.out.println("价格为"+price);
		return price;
	}
	 
	@ResponseBody
	@PostMapping("/gettimes")
	public Map<String,Object> getTimes() {
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String times = sdf.format(date);
		map.put("times", times);
		return map;
	}
	@PostMapping("/enddoing")
	public String enddoing(String doingstatus,HttpServletRequest request,Claim_List claim) {
		HttpSession session=request.getSession();
		Clerk clerk=(Clerk)session.getAttribute("clerk");
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String nowdate=sim.format(date);
		System.out.println(claim);
		if(doingstatus.equals("success")) {
			if(clerk.getMagid().equals("4")) {
				claim.getFirst_auditor().setMagid(clerk.getMagid());//设置第一操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("20");//待二审
			}else if(clerk.getMagid().equals("5")){
				claim.getSecond_auditor().setMagid(clerk.getMagid());//设置第二操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("23");//待三审
			}else if(clerk.getMagid().equals("6")){
				claim.getThird_auditor().setMagid(clerk.getMagid());//设置第三操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("25");//待支付
			}
		}else {
			if(clerk.getMagid().equals("4")) {
				claim.getFirst_auditor().setMagid(clerk.getMagid());//设置第一操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("19");//一审不通过
			}else if(clerk.getMagid().equals("5")){
				claim.getSecond_auditor().setMagid(clerk.getMagid());//设置第二操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("22");//二审不通过
			}else if(clerk.getMagid().equals("6")){
				claim.getThird_auditor().setMagid(clerk.getMagid());//设置第三操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("26");//三审不通过
			}
		}
		boolean flag = iClaimListService.updateClaim(claim);
		
		
		
		return "redirect:doing";
	}
}
