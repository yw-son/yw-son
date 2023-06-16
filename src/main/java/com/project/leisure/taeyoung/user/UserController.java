package com.project.leisure.taeyoung.user;

import java.nio.channels.MembershipKey;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.Role;

import com.project.leisure.taeyoung.email.EmailService;
import com.project.leisure.taeyoung.email.PrincipalDetails;
import com.project.leisure.taeyoung.email.SessionUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// user 관련 컨트롤러

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
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1(),
					userCreateForm.getAddr1(), userCreateForm.getAddr2(), userCreateForm.getAddr3());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "kty/signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "kty/signup_form";
		}

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "kty/login_form";
	}

	/* 회원가입 유효성 검증 post */

	@PostMapping("/check_username")
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
		return "/kty/find_pw";
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
		return "/kty/find_id";
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

	// 마이페이지
	@GetMapping("/mypage/me")
	public String myPage(Model model, Principal principal) throws JsonProcessingException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			Object aa = authentication.getPrincipal();
			System.out.println(username);
			// 필요한 사용자 정보를 가져와서 모델에 추가합니다.
			model.addAttribute("username", username);
			model.addAttribute("authority", authorities);
			model.addAttribute("aa", aa);

			if (principal instanceof OAuth2AuthenticationToken) {

				String a = principal.getName();
				System.out.println("sdd" + a);
				OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
				// System.out.println(oauthToken);

				String output = oauthToken.toString();

				String output2 = a.toString();

				String plus_account = output + output2;
				System.out.println(plus_account);

				String ns_pic = null;
				Matcher ns_matcher = Pattern.compile("picture=([^,}]+)").matcher(plus_account);
				if (ns_matcher.find()) {
					ns_pic = ns_matcher.group(1);
				}

				String ns_message = null;
				Matcher ns_matcher2 = Pattern.compile("message=([^,}]+)").matcher(plus_account);
				if (ns_matcher2.find()) {
					ns_message = ns_matcher2.group(1);
				}

				String is_email_valid = null;
				Matcher matcher6 = Pattern.compile(".*is_email_valid=([^,]+).*").matcher(plus_account);
				if (matcher6.matches()) {
					is_email_valid = matcher6.group(1);
				}

				String ns_Credential = null;
				Matcher ns_matcher6 = Pattern.compile("Credentials=([^,}]+)").matcher(plus_account);
				if (ns_matcher6.find()) {
					ns_Credential = ns_matcher6.group(1);
				}
				System.out.println("ns_Credential :" + ns_Credential);
				model.addAttribute("ns_Credential", ns_Credential);

				// 'id' 필드의 값을 추출
				// String id = output.replaceAll(".*id=(\\d+).*", "$1");

				// 'nickname' 필드의 값을 추출
				// String nickname = output.replaceAll(".*nickname=([^,}]+).*", "$1");
				String name = output.replaceAll(".*name=([^,}]+).*", "$1");
				// 'email' 필드의 값을 추출
				String email = output.replaceAll(".*email=([^,}]+).*", "$1");

				// 구글의 이름 추출
				String givenName = null;
				Matcher matcher = Pattern.compile(".*given_name=([^,]+).*").matcher(output);
				if (matcher.matches()) {
					givenName = matcher.group(1);
				}

				// google 제공자 추출 //
				String sub = null;
				Matcher matcher2 = Pattern.compile(".*sub=([^,]+).*").matcher(output);
				if (matcher2.matches()) {
					sub = matcher2.group(1);

				}
				// 카카오 제공자 추출
				String connectedAt = null;
				Matcher connectedAtMatcher = Pattern.compile(".*connected_at=([^,]+).*").matcher(output);
				if (connectedAtMatcher.matches()) {
					connectedAt = connectedAtMatcher.group(1);

				}

				// 네이버 제공자 추출
				String response = null;
				Matcher matcher3 = Pattern.compile(".*response=([^,]+).*").matcher(output);
				if (matcher3.matches()) {
					response = matcher3.group(1);
					// 여기서 sub 값을 사용할 수 있습니다.

				}
				model.addAttribute("ns_pic", ns_pic);
				model.addAttribute("ns_message", ns_message);
				model.addAttribute("is_email_valid", is_email_valid);
				model.addAttribute("ns_pic", ns_pic);
				String familyName = output.replaceAll(".*family_name=([^,]+).*", "$1");
				String role = output.replaceAll(".*Granted Authorities=\\[([^\\]]+).*", "$1");
				model.addAttribute("givenName", givenName);
				model.addAttribute("name", name);
				model.addAttribute("givenName", givenName);
				model.addAttribute("email", email);
				model.addAttribute("role", role);
				model.addAttribute("sub", sub);
				model.addAttribute("connectedAt", connectedAt);
				model.addAttribute("response", response);

			}

			List<Users> users2 = userService.check(username);
			model.addAttribute("users2", users2);
		}

		return "kty/mypage";
	}

	@GetMapping("/mypage/my_reservations")
	public String myresvation() {
		return "kty/my_reservations";
	}

}