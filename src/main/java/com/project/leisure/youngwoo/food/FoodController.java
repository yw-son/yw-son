package com.project.leisure.youngwoo.food;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	
	@GetMapping("/map123")
	public String daeguFood() {

		return "daeguFood.html";
	}
}