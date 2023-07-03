package com.project.leisure.taeyoung.public_food;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
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
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
    
   
    public Page<Food> getList(int page,String kw) {
      Pageable pageable = PageRequest.of(page, 20);
      Specification<Food> spec = search(kw);
        return foodRepository.findAll(spec,pageable);
    }

    
    public Page<Food> getList2(int page, String kw) {
    	Pageable pageable = PageRequest.of(page, 20);
    	 Specification<Food> spec = search(kw);
          return foodRepository.findAll(spec,pageable);
      }
    
    
    /*
    public List<Food> getList3(String kw) {
    	
        return foodRepository.findAll();
    }
   */
    
    
    
    private Specification<Food> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Food> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복 제거

                String[] keywords = kw.split("\\s+"); // 입력된 키워드를 공백을 기준으로 분리

                List<Predicate> predicates = new ArrayList<>();
                for (String keyword : keywords) {
                    Predicate keywordPredicate = cb.and(
                        cb.or(
                            cb.like(q.get("shopAddr"), "%" + keyword + "%"),    // 주소로 찾기
                            cb.like(q.get("shopName"), "%" + keyword + "%"),    // 가게명으로 찾기
                            cb.like(q.get("shopCategory"), "%" + keyword + "%"),// 업종으로 찾기
                            cb.like(q.get("shopMenu"), "%" + keyword + "%"),    // 메뉴로 찾기
                            cb.like(q.get("shopInfo"), "%" + keyword + "%")     // 소개로 찾기
                        )
                    );
                    predicates.add(keywordPredicate);
                }

                Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
                return finalPredicate;
            }
        };
    }
    

   
}