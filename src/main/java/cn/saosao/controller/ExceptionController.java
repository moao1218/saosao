package cn.saosao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.saosao.component.MyExceptionHandler;
import cn.saosao.pojo.MyException;

@Controller
public class ExceptionController {
	@ResponseBody
    @ExceptionHandler(value = MyException.class)
	public Map<String,Object> myExceptionHandler(MyException exe){
		Map<String,Object> map  = new HashMap<String,Object>();
        map.put("code",exe.getCode());
        map.put("message",exe.getMessage());
        map.put("method",exe.getMethod());
        map.put("descinfo",exe.getDescinfo());
        return map;
	}
	@RequestMapping(value = "/testException")
	public String index() throws Exception{

	     
	    throw new MyException("1001","empty","/API/getUserName","网络异常，请稍后再试！");
	      }

}
