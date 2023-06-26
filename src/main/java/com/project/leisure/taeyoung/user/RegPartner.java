package com.project.leisure.taeyoung.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RegPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String company_name; //업체명

	private String company_address; //업체주소

	private String partner_name; //담당자명

	private String partner_tel; //담당자번호

	private String partner_sectors; //  업종

	private String partner_region; // 지역(ex. 대구, 수성구,북구,칠곡...등)
	
	private String username; // 신청자 아이디

}
