package com.project.leisure.taeyoung.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RegRepository extends JpaRepository<RegPartner, Long> {
	
}