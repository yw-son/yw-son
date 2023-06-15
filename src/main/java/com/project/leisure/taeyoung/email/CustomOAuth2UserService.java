package com.project.leisure.taeyoung.email;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.Users;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

// 소셜로그인 동작 서비스 처리
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User > {
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		// OAuth2 서비스 id (구글, 카카오, 네이버)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		// OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();

		// OAuth2UserService
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());
		Users user = saveOrUpdate(attributes);
		httpSession.setAttribute("user", new SessionUser(user)); // SessionMember (직렬화된 dto 클래스 사용)

		// TODO: JWT 생성

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	// 유저 생성 및 수정 서비스 로직
	private Users saveOrUpdate(OAuthAttributes attributes) {
		Users user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getNickname(), attributes.getEmail(), attributes.getSns()))
				.orElse(attributes.toEntity());

		return userRepository.save(user);
	}
}