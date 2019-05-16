package cn.saosao.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.saosao.pojo.Claim_List;
import cn.saosao.pojo.Claim_Verify;
import cn.saosao.pojo.Clerk;
import cn.saosao.pojo.Items;
import cn.saosao.pojo.Pv;
import cn.saosao.pojo.Status;
import cn.saosao.service.IClaimListService;
import cn.saosao.service.IClaimVerifyService;
import cn.saosao.service.IItemsListService;
import cn.saosao.service.IPvService;
import cn.saosao.service.IStatusService;
import cn.saosao.util.InterfaceUtil;

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
	@Autowired
	IPvService pvServiceImpl;
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
	public String getClerkById(@PathVariable("id") String claimid,Map<String,Object> map,Model model,HttpSession session) {
		Integer cp=1;
		Integer ps=1;
		map.put("ps", ps);
		map.put("cp",cp);
		map.put("claimid",claimid);//"15573813681523542796"
		iClaimListService.getAll(map);
		List<Claim_List> c = (List)map.get("claim_list");
		model.addAttribute("claim", c.get(0));
		Clerk clerk = (Clerk)session.getAttribute("clerk");

		List<Claim_Verify> list = iClaimVerifyService.getList(claimid);
		System.out.println("列表长度"+list.size());
		for (Claim_Verify claim_Verify : list) {
			System.out.println(claim_Verify);
		}

	

		//--------------------------------------------------------------
		System.out.println(clerk);
		if(clerk.getRoleid().equals("10")){
			 List<Items> items = pvServiceImpl.findAllItems();
			 model.addAttribute("items",items);
			return "backPage/showdoingInfo";
		}else {
			if(list.size()==0) {
				model.addAttribute("verify",new Claim_Verify());
			}else {
				model.addAttribute("verify",list.get(0));
			}
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

			  //发票价格
			  Double invoic=claim_Verify.getInvoice()==null?0:Double.parseDouble(claim_Verify.getInvoice());

			  //房屋市场价价格 
			  Double housemarket=claim_Verify.getHouse_market()==null?0:Double.parseDouble(claim_Verify.getHouse_market());

			  //使用年限 
			  Double useage=claim_Verify.getUser_age()==null?0:Double.parseDouble(claim_Verify.getUser_age());

			  //免赔率 
			  Double excess = Double.parseDouble(claim_Verify.getItems_list().getExcess());

			  //折旧率
			  Double dep_rate=Double.parseDouble(claim_Verify.getItems_list().getDep_rate());

			  if(claim_Verify.getItems_list().getItemid()>=111) {
				  price+=(housemarket-(housemarket*excess)-(dep_rate*useage*housemarket)); 

			  }else {
				  price+=(invoic-(invoic*excess)-(dep_rate*useage*invoic));

			  }
			 
			
			
		}

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
		System.out.println(clerk);
		System.out.println(claim);
		System.out.println("状态是"+doingstatus);
		if(doingstatus.equals("success")) {
			if(clerk.getRoleid().equals("4")) {
				claim.getFirst_auditor().setMagid(clerk.getMagid());//设置第一操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("20");//待二审
			}else if(clerk.getRoleid().equals("5")){
				claim.getSecond_auditor().setMagid(clerk.getMagid());//设置第二操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("23");//待三审
			}else if(clerk.getRoleid().equals("6")){
				claim.getThird_auditor().setMagid(clerk.getMagid());//设置第三操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("25");//待支付
			}
		}else {
			if(clerk.getRoleid().equals("4")) {
				claim.getFirst_auditor().setMagid(clerk.getMagid());//设置第一操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("19");//一审不通过
			}else if(clerk.getRoleid().equals("5")){
				claim.getSecond_auditor().setMagid(clerk.getMagid());//设置第二操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("22");//二审不通过
			}else if(clerk.getRoleid().equals("6")){
				claim.getThird_auditor().setMagid(clerk.getMagid());//设置第三操作人
				claim.getUpper_operator().setMagid(clerk.getMagid());//设置上一级操作人
				claim.setUpper_date(nowdate);//设置上一级操作时间
				claim.getStatus().setStatusid("26");//三审不通过
			}
		}
		boolean flag = iClaimListService.updateClaim(claim);
		
		
		
		return "redirect:doing";
	}
	
	//勘查人员提交的勘查清单表
	
	  @ResponseBody
	  @PostMapping(value="/findAllItems") 
	  public List<Items> findAllItems(){
		  List<Items> items = pvServiceImpl.findAllItems(); 
		  return items; 
	  }
	 
	  @PostMapping(value="/insertPv")
		public String insertPv(Pv pv,HttpServletRequest request) {
		  
			Map<String,Object> map = new HashMap<String,Object>();
			
			Items items = new Items();
			
			pv.setVerify_scout(pv.getVerify_scout());
			pv.setDel_status("0");
		
			MultipartRequest req = (MultipartRequest) request;	
			MultipartFile h_pic = req.getFile("h_pic");
			MultipartFile bu_pic = req.getFile("bud_pic");
			if(h_pic!=null) {
				String house_pic = InterfaceUtil.upload(request, h_pic);
				pv.setHouse_pic(house_pic);
			}
			if(bu_pic!=null) {
				String building_pic = InterfaceUtil.upload(request, bu_pic);
				pv.setBuilding_pic(building_pic);
			}
			
			
			String num = request.getParameter("num");
			System.out.println("num:"+num);
			for (int i = 1; i < Integer.parseInt(num); i++) {
				long currentTimeMillis = System.currentTimeMillis();
				int r = new Random().nextInt(8999)+1000;
				pv.setCla_ver_id(""+currentTimeMillis+r);
				
				if(req.getFile("a_pic"+i)!=null) {
					String site_photo = InterfaceUtil.upload(request, req.getFile("a_pic"+i));
					items.setSite_photo(site_photo);
				}
				
				if(req.getFile("b_pic"+i)!=null) {
					String third_pic = InterfaceUtil.upload(request, req.getFile("b_pic"+i));
					items.setThird_pic(third_pic);
				}
				if(req.getFile("c_pic"+i)!=null) {
					String invoice_pic = InterfaceUtil.upload(request, req.getFile("c_pic"+i));
					items.setInvoice_pic(invoice_pic);
				}
				String itemid = request.getParameter("a"+i);
				String user_age = request.getParameter("b"+i);
				String invoice = request.getParameter("c"+i);
				String mark = request.getParameter("d"+i);
				String itme_model = request.getParameter("e"+i);
				
			items.setItemid(/* Integer.parseInt(itemid) */102);
				System.out.println(items.getItemid());
				items.setUser_age(user_age);
				items.setInvoice(invoice);
				items.setMark(mark);
				items.setItme_model(itme_model);
				System.out.println("aaaaaaaaaaa:"+items);
				map.put("pv", pv);
				map.put("items", items);
				
				boolean insertPv = pvServiceImpl.insertPv(map);
			}
//			List<Items> items = pvService.findAllItems();
//			model.addAttribute("items", items);
			return "redirect:/doing";
		}
	
	
	@PostMapping("/showfile") 
	public String showfile( Claim_Verify cla_ver,Claim_List claim, HttpServletRequest request,Pv pv) {
		  Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  String times = sdf.format(date);
		  Clerk clerk = (Clerk)request.getSession().getAttribute("clerk");
		

		claim.setUpper_date(times);
		claim.setClaimid(cla_ver.getClaim_list().getClaimid());
		claim.getScout().setMagid(clerk.getMagid());
		claim.getUpper_operator().setMagid(clerk.getMagid());
		claim.getStatus().setStatusid("18");//勘查中页面该状态为勘查借宿
		iClaimListService.updateClaim(claim);
		
		//--------------------------------------------------------------------------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		
		Items items = new Items();
		
		pv.setVerify_scout(pv.getVerify_scout());
		pv.setDel_status("0");
	
		MultipartRequest req = (MultipartRequest) request;	
		MultipartFile h_pic = req.getFile("h_pic");
		MultipartFile bu_pic = req.getFile("bud_pic");
		if(h_pic!=null) {
			String house_pic = InterfaceUtil.upload(request, h_pic);
			pv.setHouse_pic(house_pic);
		}
		if(bu_pic!=null) {
			String building_pic = InterfaceUtil.upload(request, bu_pic);
			pv.setBuilding_pic(building_pic);
		}
		
		
		String num = request.getParameter("num");
		System.out.println("num:"+num);
		for (int i = 1; i < Integer.parseInt(num); i++) {
			long currentTimeMillis = System.currentTimeMillis();
			int r = new Random().nextInt(8999)+1000;
			pv.setCla_ver_id(""+currentTimeMillis+r);
			
			if(req.getFile("a_pic"+i)!=null) {
				String site_photo = InterfaceUtil.upload(request, req.getFile("a_pic"+i));
				items.setSite_photo(site_photo);
			}
			
			if(req.getFile("b_pic"+i)!=null) {
				String third_pic = InterfaceUtil.upload(request, req.getFile("b_pic"+i));
				items.setThird_pic(third_pic);
			}
			if(req.getFile("c_pic"+i)!=null) {
				String invoice_pic = InterfaceUtil.upload(request, req.getFile("c_pic"+i));
				items.setInvoice_pic(invoice_pic);
			}
			String itemid = request.getParameter("a"+i);
			String user_age = request.getParameter("b"+i);
			String invoice = request.getParameter("c"+i);
			String mark = request.getParameter("d"+i);
			String itme_model = request.getParameter("e"+i);
			
			items.setItemid(Integer.parseInt(itemid));
			System.out.println(items.getItemid());
			items.setUser_age(user_age);
			items.setInvoice(invoice);
			items.setMark(mark);
			items.setItme_model(itme_model);
			System.out.println("aaaaaaaaaaa:"+items);
			map.put("pv", pv);
			map.put("items", items);
			
			boolean insertPv = pvServiceImpl.insertPv(map);
		}
//		List<Items> items = pvService.findAllItems();
//		model.addAttribute("items", items);
		
		return "redirect:/doing";
	}
	

}
