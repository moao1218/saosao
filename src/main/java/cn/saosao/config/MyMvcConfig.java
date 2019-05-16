package cn.saosao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.saosao.Interceptor.LoginInterceptor;
import cn.saosao.component.MyLocaleResolver;


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
			  @Override
			public void addInterceptors(InterceptorRegistry registry) {
				  registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
				  .excludePathPatterns("/login.html","/login","/webjars/**","/asserts/**","/back/**","/images/**","/upload/**");
			}
		  };
		  return adapter;
	  }
	

	//添加国际化组件到容器中
	  @Bean
	  public MyLocaleResolver localeResolver(){

	      return new MyLocaleResolver();
	  }
	  
	  

}
