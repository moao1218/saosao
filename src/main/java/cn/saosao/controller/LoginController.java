package cn.saosao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.saosao.pojo.Clerk;
import cn.saosao.service.IClerkService;
import cn.saosao.util.InterfaceUtil;
import cn.saosao.util.RSAImpl;

@Controller
public class LoginController {

	@Autowired
	IClerkService clerkServiceImpl;
	Map<Integer,String> map=new HashMap();

	@PostMapping("/login")

	public String checkLogin(Clerk clerk,HttpServletRequest request,Map<String,Object> map1,String msg) throws Exception {

		Clerk c = clerkServiceImpl.findUserPwd(clerk);
		System.out.println(c);
		System.out.println(clerk);
		/* map1=new HashMap<String, Object>(); */
		if(c==null) {
			//登录失败
			if(msg==null) {
				map1.put("msg", "用户名不存在!");
				System.out.println("1");
			}else {
				map1.put("msg", msg);
			}
			return "backPage/login";
		}else {
			//调用接口类的验证密文方法
			String realpwd=RSAImpl.getOriginal(clerk.getUserpwd(), (String) map.get(1));
			
			boolean flagPwd = InterfaceUtil.checkMatch(realpwd, c.getUserpwd());
			if(flagPwd) {
				Clerk cler = clerkServiceImpl.checkLogin(clerk); 
				request.getSession().setAttribute("clerk", cler);
//				model.addAttribute("clerk", c);
				return "backPage/index";
			}else {
				//登录失败
				map1.put("msg", "用户名密码错误!");
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
		model.addAttribute("clerk", c);
		return "backPage/userinfo";
	}
	
	@ResponseBody
	@PostMapping("/getPublicKey")
	public String getPublicKey() {
		
		RSAImpl rsa=new RSAImpl();
		map=rsa.getCommAndPrivaKey();
		System.out.println("公钥是"+map.get(0));
		return "\""+map.get(0)+"\"";
		
	}
}




