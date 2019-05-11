package cn.saosao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoryController {
	@RequestMapping("history")
	public String getHistory() {
		return "backPage/history";
	}
}
