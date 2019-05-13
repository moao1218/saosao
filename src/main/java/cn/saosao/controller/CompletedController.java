package cn.saosao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**理赔模块
 * 处理查看已完成的理赔单 类
 * 和没有处理的理赔单的信息，
 * @author wh
 *
 */
@Controller
public class CompletedController {

	//查看支出信息
	@GetMapping("/Completed")
	public String zhichu() {
		return "backPage/main";
	}
	
	
	//处理待完成的理赔单
	@GetMapping("/ToBeCompleted")
	public String ToBeCompleted() {
		return "";
	}
	
	
	
	
	
	
}







