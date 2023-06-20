package com.project.leisure.hyokyeong.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {
	@RequestMapping("khk/adminMain")
	public String admin_index() {
		return "khk/adminMain";
	}
}
