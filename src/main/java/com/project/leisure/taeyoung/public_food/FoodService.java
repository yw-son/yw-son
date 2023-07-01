package com.project.leisure.taeyoung.public_food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public boolean existsByShopId(String shopId) {
        return foodRepository.existsByShopId(shopId);
    }

    public void saveFood(Food food) {
        String shopId = food.getShopId();

        if (existsByShopId(shopId)) {
            System.out.println("중복된 데이터입니다: " + shopId);
            return;
        }

        foodRepository.save(food);
        System.out.println("음식 저장됨: " + shopId);
    }
}