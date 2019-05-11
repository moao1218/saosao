package cn.saosao.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import cn.saosao.pojo.Clerk;
import cn.saosao.service.IClerkService;
import cn.saosao.util.InterfaceUtil;

@Controller
public class LoginController {

	@Autowired
	IClerkService clerkServiceImpl;
	InterfaceUtil interfaceUtil = new InterfaceUtil();
	
	
	@PostMapping("/login")
	public String CheckLogin(Clerk clerk,HttpServletRequest request) {
		
		System.out.println("a:"+clerk.getUsername()+"b:"+clerk.getUserpwd());
		Clerk c = clerkServiceImpl.findUserPwd(clerk);
		boolean flagPwd = interfaceUtil.checkMatch(clerk.getUserpwd(), c.getUserpwd());
		System.out.println("flagPwd:"+flagPwd);
		
		if(flagPwd) {
			Clerk cler = clerkServiceImpl.checkLogin(clerk); 
			request.getSession().setAttribute("clerk", cler);
			return "backPage/index";
		}
		return "";
	}
	
	
}




