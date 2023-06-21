package com.project.leisure.taeyoung.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegService {

	
	
	private final RegRepository regRepository;
	
	
	
	public RegPartner create(String username,String company_name, String company_address, String partner_name, String partner_tel, String partner_sectors,String partner_region ) {
		RegPartner reg = new RegPartner();
		reg.setCompany_name(company_name);
		reg.setUsername(username);
		reg.setCompany_address(company_address);
		reg.setPartner_name(partner_name);
		reg.setPartner_tel(partner_tel);
		reg.setPartner_sectors(partner_sectors);
		reg.setPartner_region(partner_region);
		this.regRepository.save(reg);
		return reg;
	}
}
