package com.project.leisure.taeyoung.tour;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tour")
public class TourController {

	@GetMapping("/daegu_top10")
	public String address2() {
		//System.out.println("카카오 API 테스트");
		
		return "kty/daegu_tour10";
	}
	
	@GetMapping("food")
	public String food() {
		return "kty/food";
	}
}
