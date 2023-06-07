package com.project.leisure.taeyoung.user;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"), PARTNER("ROLE_PARTNER"), USER("ROLE_USER"), SNS_USER("ROLE_SNS");

	UserRole(String value) {
		this.value = value;
	}

	private String value;
}