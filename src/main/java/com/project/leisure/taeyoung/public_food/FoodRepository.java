package com.project.leisure.taeyoung.public_food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByShopId(String shopId);
}