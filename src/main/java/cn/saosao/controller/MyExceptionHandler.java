package cn.saosao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.saosao.pojo.MyException;

@ControllerAdvice
public class MyExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
		
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist"); 
        map.put("message","啊哦~ 网络异常了,请检查网络环境...");
        request.setAttribute("javax.servlet.error.status_code1",404);
        map.put("code1","user.notexist"); 
        map.put("message1","抱歉,找不到该页面...");
		 

        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }
}
