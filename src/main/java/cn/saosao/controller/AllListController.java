package cn.saosao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllListController {
	@GetMapping("/getall")
	public String allList() {
		return "backPage/referAll";
	}
}
