package cn.saosao.controller;
/**
 * 用于外部接口的请求控制器
 * @author THINK
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.saosao.util.InterfaceUtil;

@Controller
public class InterfaceController {
	
	
	@ResponseBody
	@PostMapping("/weather")
	public String getWeather() {
		//调用接口类的天气接口
		String weatherJsonStr = InterfaceUtil.weather();
		return weatherJsonStr;
	}
}
