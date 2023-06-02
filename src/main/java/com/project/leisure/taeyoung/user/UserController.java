package com.project.leisure.taeyoung.user;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.project.leisure.taeyoung.user.email.EmailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;


	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "kty/signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "kty/signup_form";
		}

		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "kty/signup_form";
		}

		   try {
	            userService.create(userCreateForm.getUsername(), 
	                    userCreateForm.getEmail(), userCreateForm.getPassword1());
	        }catch(DataIntegrityViolationException e) {
	            e.printStackTrace();
	            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
	            return "kty/signup_form";
	        }catch(Exception e) {
	            e.printStackTrace();
	            bindingResult.reject("signupFailed", e.getMessage());
	            return "kty/signup_form";
	        }

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "kty/login_form";
	}

	/* 회원가입 유효성 검증 post */

	@PostMapping("/check")
	public ResponseEntity<Integer> checkUsername(@RequestParam("username") String username) {
		List<Users> users = userService.check(username);
		int result = (users != null && !users.isEmpty()) ? 1 : 0;
		return ResponseEntity.ok(result);
	}

	@GetMapping("/sign")
	public String logi2n() {
		return "signup";
	}

	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/emailcode")
	@ResponseBody
	public String mailConfirm(@RequestParam String email) throws Exception {
		String code = emailService.sendSimpleMessage(email);
		return code;
	}

	@GetMapping("/find_pw")
	public String findPW() {
		return "find_pw";
	}

	@PostMapping("/temp_pwd")
	@ResponseBody
	public String sendTempPwd(@RequestParam String email, @RequestParam String username) throws Exception {

		List<Users> users = userService.find(username, email);

		if (users != null && !users.isEmpty()) {
			String code = emailService.sendSimpleMessage(email);

			// 임시 비밀번호로 패스워드 변경
			Users user = users.get(0);
			user.setPassword(passwordEncoder.encode(code));
			userService.save(user);

			return code;
		} else {
			String result = null;
			return result;
		}

	}

	@GetMapping("/find_id")
	public String findID() {
		return "find_id";
	}

	@PostMapping("/find_id")
	@ResponseBody
	public String findid(@RequestParam String email) {
		Optional<Users> users = userService.check2(email);

		if (users.isPresent()) {
			Users user = users.get();
			String username = user.getUsername();
			if (username != null) {
				int length = username.length();
				int maskedLength = Math.min(length, 6);
				String maskedUsername = username.substring(0, length - maskedLength)
						+ StringUtils.repeat("*", maskedLength);

				String result2 = "<br><br>전체 아이디를 알고자 하시는 분은 고객센터로 문의 바랍니다.";
				return "<br>고객 ID :  " + maskedUsername + "<br> 계정 보안을 위해 마스킹 된 정보만 제공합니다" + result2;
			} else {
				String result = "<br>등록된 사용자가 아니거나 SNS 유저는 확인 할 수 없습니다.<br>";

				return result;
			}
			
		} else {
			String result = "<br>등록된 사용자가 아니거나 SNS 유저는 확인 할 수 없습니다.<br>";

			return result;
		}
	}

}