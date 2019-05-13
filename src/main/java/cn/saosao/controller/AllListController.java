package cn.saosao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cn.saosao.service.IClaimListService;

@Controller
public class AllListController {
	@GetMapping("/getall")
	public String allList() {
		return "backPage/referAll";
	}
	@PostMapping("/getall")
	public String condition() {
		
		return "backPage/referAll";
	}
}
