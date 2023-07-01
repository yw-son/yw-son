package com.project.leisure.taeyoung.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/daegu_top10")
    public String address2() {
        // System.out.println("카카오 API 테스트");
        return "kty/daegu_tour10";
    }

    @GetMapping("food")
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
}