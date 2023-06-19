package com.project.leisure.taeyoung.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.Role;
import com.project.leisure.DataNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

//user serv

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Users create(String username, String email, String password, String addr1, String addr2, String addr3) {
		Users user = new Users();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setAddr1(addr1);
		user.setAddr2(addr2);
		user.setAddr3(addr3);
		this.userRepository.save(user);
		return user;
	}

	public List<Users> check(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<Users> check2(String email) {
		return userRepository.findByEmail(email);
	}

	public List<Users> find(String username, String email) {

		return userRepository.findByUsernameAndEmail(username, email).map(Collections::singletonList)
				.orElse(Collections.emptyList());
	}

//임시비밀번호로 패스워드 변경
	public void save(Users user) {
		this.userRepository.save(user);
		System.out.println("Saving user: " + user.getUsername() + ", Password: " + user.getPassword());
	}

	public List<Users> nick(String nickname) {
		return userRepository.findByNickname(nickname);
	}

	public Optional<Users> changepwd(String password){
		return userRepository.findByPassword(password);
	}
	
}
