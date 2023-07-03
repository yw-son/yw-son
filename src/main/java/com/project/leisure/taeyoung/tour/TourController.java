package com.project.leisure.taeyoung.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import com.project.leisure.taeyoung.public_food.Food;
import com.project.leisure.taeyoung.public_food.FoodService;

@Controller
@RequestMapping("/tour")
public class TourController {

    private final FoodService foodService;

    @Autowired
    public TourController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/daegu_travel")
    public String address2() {
        // System.out.println("카카오 API 테스트");
        return "kty/daegu_tour10";
    }

    
    
    
    /* 아래 맛집 데이터 뽑는 과정 */
    
    
    @GetMapping("/food")
    public String food() {
        return "kty/food";
    }

    @PostMapping("/saveFood")
    @ResponseBody
    public ResponseEntity<String> saveFood(@RequestBody Food food) {
        try {
            foodService.saveFood(food);
            return ResponseEntity.ok("Food saved successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception details to the console for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving food: " + e.getMessage());
        }
    }
 
    
    @GetMapping("/daegu_food")
    public String food_test2(Model model, @RequestParam(value="page", defaultValue="0") int page,
    		@RequestParam(value = "kw", defaultValue = "") String kw) {
    	Page<Food> paging = this.foodService.getList2(page,kw);
    	   model.addAttribute("paging", paging);
           model.addAttribute("foodList", paging.getContent()); 
           model.addAttribute("kw", kw);
        return "kty/food_test2";
    }
}