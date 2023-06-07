package com.project.leisure.taeyoung.user;

import java.nio.channels.MembershipKey;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

import com.nimbusds.oauth2.sdk.Role;
import com.project.leisure.taeyoung.email.EmailService;
import com.project.leisure.taeyoung.email.PrincipalDetails;
import com.project.leisure.taeyoung.email.SessionUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	
/* 마이페이지 
	@GetMapping("/mypage/me")
	public String mypage(Principal principal, Model model,OAuth2AuthenticationToken authentication) {
		//일반 로그인 사용자 정보 
		
		String username = principal.getName();
		 List<Users> users = userService.check(username);
		 model.addAttribute("users",users);
		 
		
	    return "kty/mypage";
	}
		*/
	
	// 마이페이지 
	@GetMapping("/mypage/me")
	 public String myPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = new Users();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String email = users.getEmail();
            Object authority = authentication.getAuthorities();
       
            // 필요한 사용자 정보를 가져와서 모델에 추가합니다.
            model.addAttribute("username", username);
            model.addAttribute("authority", authority);
            model.addAttribute("email", email);
            // 추가적인 사용자 정보를 가져오고 싶다면, Principal 객체를 확인하여 모델에 추가합니다.
            Object principal = authentication.getPrincipal();
      
 
                
            

        }

        return "kty/mypage";
    }
	
	
	
}