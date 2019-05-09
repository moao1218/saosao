package cn.saosao.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 自定义国际化语言
 * @author lenovo
 *
 */
//在链接携带区域信息
public class MyLocaleResolver implements LocaleResolver{

	@Override
	public Locale resolveLocale(HttpServletRequest arg0) {
		String parameter = arg0.getParameter("l");
		//System.out.println("自己的国际化"+parameter);
		Locale l=Locale.getDefault();//首先是默认的，下面不为空就是自带的
		if(!StringUtils.isEmpty(parameter)) {
			String[]  split = parameter.split("_");
			 l=new Locale(split[0],split[1]);
		}
		
		return l;
	}

	@Override
	public void setLocale(HttpServletRequest arg0, HttpServletResponse arg1, Locale arg2) {
		
	}
	
	
	
	
}
