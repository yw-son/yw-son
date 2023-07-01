package com.project.leisure.taeyoung.public_food;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "food")
@Getter
@Setter
public class Food {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "shop_id")
	    private String shopId;

	    @Column(name = "shop_addr")
	    private String shopAddr;

	    @Column(name = "shop_category")
	    private String shopCategory;

	    @Column(name = "shop_name")
	    private String shopName;

	    @Column(name = "shop_tel")
	    private String shopTel;

	    @Column(name = "shop_opening") // 수정된 부분
	    private String shopOpening;

	    @Column(name = "shop_parking")
	    private String shopParking;

	    @Column(name = "shop_menu")
	    private String shopMenu;

	    @Column(name = "shop_info")
	    private String shopInfo;

	    public Food() {
	        // Default constructor required by JPA
	    }

	    public Food(String shopId, String shopAddr, String shopCategory, String shopName, String shopTel, String shopOpening, String shopParking, String shopMenu, String shopInfo) {
	        this.shopId = shopId;
	        this.shopAddr = shopAddr;
	        this.shopCategory = shopCategory;
	        this.shopName = shopName;
	        this.shopTel = shopTel;
	        this.shopOpening = shopOpening;
	        this.shopParking = shopParking;
	        this.shopMenu = shopMenu;
	        this.shopInfo = shopInfo;
	    }
}