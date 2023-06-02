package com.project.leisure.taeyoung.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true)
	private String email;

	private LocalDateTime create_userTime = LocalDateTime.now();

	private int partner_code; // 파트너 여부 1: 파트너임

	private String nickname;

	private int admin_code; // 관리자 여부 1: 관리자임
	
	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER;

	private String sns;

	public Users update(String username) {
		this.username = username;

		return this;
	}

	@Builder
	public Users(String nickname, String email, UserRole role, String sns) {
		this.nickname = nickname;
		this.email = email;
		this.sns = sns;
		this.role = role;
	}

	public Users update(String nickname, String email, String sns) {
		this.nickname = nickname;
		this.sns = sns;
		this.email = email;
		return this;
	}

	public String getRoleKey() {
		return this.role.getValue();
	}

	
}