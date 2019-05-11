package cn.saosao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PendingController {
	@GetMapping("/pending")
	public String pending() {
		
		return "backPage/pending";
	}
}
