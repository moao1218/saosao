package cn.saosao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
	@Bean   //将组件注册在容器
	  public WebMvcConfigurer webMvcConfigurer() {
		  WebMvcConfigurer adapter = new WebMvcConfigurer() {
			  @Override
			  public void addViewControllers(ViewControllerRegistry registry) {
				  registry.addViewController("/").setViewName("backPage/login");
				  registry.addViewController("/login.html").setViewName("backPage/login");
			  } 
		  };
		  return adapter;
	  }
	
	@Bean
	public MyLocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
}
