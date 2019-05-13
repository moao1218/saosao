package cn.saosao.controller;
/**
 * 用于外部接口的请求控制器
 * @author THINK
 *
 */

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.saosao.pojo.Weather;
import cn.saosao.util.InterfaceUtil;
import cn.saosao.util.WeatherUtil;

@Controller
public class InterfaceController {
	
	 
	@ResponseBody
	@PostMapping("/weather")
	public String getWeather() {
		//调用接口类的天气接口
		String weatherJsonStr = InterfaceUtil.weather();
		return weatherJsonStr;
	}
	
	@ResponseBody
	@PostMapping("/weatherservice")
	public List<Weather> getWeatherWebService() {
		List<Weather> checkWeather = WeatherUtil.checkWeather("深圳");
		System.out.println(checkWeather);
		return checkWeather;
	}
}
