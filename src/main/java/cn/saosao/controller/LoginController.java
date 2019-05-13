package cn.saosao.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cn.saosao.pojo.Clerk;
import cn.saosao.service.IClerkService;
import cn.saosao.util.InterfaceUtil;

@Controller
public class LoginController {

	@Autowired
	IClerkService clerkServiceImpl;
	
	
	@PostMapping("/login")
	public String checkLogin(Clerk clerk,HttpServletRequest request,Map<String,Object> map) {
		Clerk c = clerkServiceImpl.findUserPwd(clerk);
		if(c==null) {
			//登录失败
			map.put("msg", "用户名不存在!");
			return "backPage/login";
		}else {
			//调用接口类的验证密文方法
			boolean flagPwd = InterfaceUtil.checkMatch(clerk.getUserpwd(), c.getUserpwd());
			if(flagPwd) {
				Clerk cler = clerkServiceImpl.checkLogin(clerk); 
				request.getSession().setAttribute("clerk", cler);
				return "backPage/index";
			}else {
				//登录失败
				map.put("msg", "用户名密码错误!");
				return "backPage/login"; 
			}
		}
	}
	
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		Clerk ccc = (Clerk)request.getSession().getAttribute("clerk");
		return "backPage/login";
	}
	
	@GetMapping("/userinfo")
	public String userinfo(HttpServletRequest request,Model model) {
		Clerk clerk = (Clerk)request.getSession().getAttribute("clerk");
		Clerk c = clerkServiceImpl.getClerkById(clerk.getMagid()+"");
		System.out.println(c);
		model.addAttribute("clerk", c);
		return "backPage/userinfo";
	}
	
}




