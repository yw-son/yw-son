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
// 회원DB 엔티티
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 회원 번호(PK)

	@Column(unique = true)
	private String username; // 회원 ID

	private String password; // 회원 PWD

	private String email; // 회원 Email

	private LocalDateTime create_userTime = LocalDateTime.now(); // 계정생성일

	private int partner_code; // 파트너 등급 권한 설정

	private String nickname; // social에서 제공받은 닉네임(일반 회원 가입 시 기본값은 null)

	private int admin_code; // 관리자 등급 권한 설정

	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER; // 회원등급 (기본값 USER)

	private String sns; // 소셜 정보 제공자 (ex. 네이버, 카카오 , 구글)

	private String addr1; // 우편변호

	private String addr2; // 지번 주소

	private String addr3; // 상세 주소

	private int islock = 0; // 계정 잠금 여부(1: 계정 잠김, 0: 계정 안잠김)

	public Users update(String username) {
		this.username = username;

		return this;
	}

	// 소셜 로그인 시 등록되는 정보
	@Builder
	public Users(String nickname, String email, UserRole role, String sns) {
		this.nickname = nickname;
		this.email = email;
		this.sns = sns;
		this.role = role;
	}

	// 소셜 로그인 사용자의 email과 일반 회원 email이 같을 때 일반회원정보에 덮어쓰는 정보
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