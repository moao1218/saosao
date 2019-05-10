package cn.saosao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login() {
		
		
		
		
		
		
		return "backPage/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "login";
	}
	
	
	
}




