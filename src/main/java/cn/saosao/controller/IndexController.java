package cn.saosao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cn.saosao.pojo.Clerk;
import cn.saosao.service.IClerkService;

@Controller
public class IndexController {
	@Autowired
	IClerkService clerkServiceImpl;
	
	@GetMapping("/index")
	public String goIndex(HttpServletRequest request,Model model) {
		
		Clerk clerk = (Clerk)request.getSession().getAttribute("clerk");
		Clerk c = clerkServiceImpl.getClerkById(clerk.getMagid()+"");
//		model.addAttribute("clerk", c);
		return "backPage/index";
	}
	
}
