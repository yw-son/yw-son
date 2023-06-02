package com.project.leisure.taeyoung.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> _Users = this.userRepository.findByusername(username);
		if (_Users.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}

		Users Users = _Users.get();
		Integer admin = Users.getAdmin_code();
		Integer partner = Users.getPartner_code();

		List<GrantedAuthority> authorities = new ArrayList<>();
		if (admin > 1000 && admin < partner) {
			Users.setRole(UserRole.ADMIN); // Set the role value to ADMIN
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else if (partner > 2000 && partner < admin) {
			Users.setRole(UserRole.PARTNER); // Set the role value to PARTNER
			authorities.add(new SimpleGrantedAuthority(UserRole.PARTNER.getValue()));
		} else {
			Users.setRole(UserRole.USER); // Set the role value to PARTNER
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}

		this.userRepository.save(Users); // Save the updated Users object

		return new User(Users.getUsername(), Users.getPassword(), authorities);
	}
}