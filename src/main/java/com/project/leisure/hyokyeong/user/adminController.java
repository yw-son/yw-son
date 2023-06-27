package com.project.leisure.hyokyeong.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.leisure.taeyoung.email.PrincipalDetails;
import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.UserRole;
import com.project.leisure.taeyoung.user.UserSecurityService;
import com.project.leisure.taeyoung.user.Users;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class adminController {

	private final UserRepository userRepository;
	@Autowired
	private final UserListService userListService;

	@GetMapping("/adminMain")
	public String adminMain() {
		System.out.println("메인페이지로 이동");
		return "khk/adminMain";
	}

	@GetMapping("/userListPage")
	public String Userlist(Model model) {
		userListService.userList(model);
		return "khk/userListPage";
	}

	@PostMapping("/{id}/adminRole")
	public String updateUserRole(@PathVariable("id") Long id, @RequestParam("role") UserRole role, Model model) {
	    try {
	        userListService.updateUserRole(id, role); // 서비스를 통해 DB 업데이트 수행
	        List<Users> userList = userListService.userList(model); // 변경된 회원 목록을 조회
	        model.addAttribute("users", userList); // 변경된 회원 목록을 모델에 추가
	        return "khk/userListPage"; // 회원 목록 페이지로 이동
	    } catch (IllegalArgumentException e) {
	        return "redirect:/khk/adminMain"; // 예외 발생 시 관리자 메인 페이지로 이동
	    }
	}

//	@PostMapping("/users/{id}/role")
//	public String updateUserRole(@PathVariable("id") Long id, @RequestParam("role") UserRole role) {
//	    try {
//	        userListService.updateUserRole(id, role); // 선택한 옵션으로 DB 업데이트
//	        return "redirect:/userListPage";
//	    } catch (IllegalArgumentException e) {
//	        return "redirect:/khk/adminMain";
//	    }
//	}

//	
//	 @Autowired 
//	 @PostMapping("/toggle-account")
//	 public String toggleAccount(@RequestParam(name = "username", value = "") String username) {
//		    Optional<Users> userOptional = userService.getUserByUsername(username);
//		    if (userOptional.isPresent()) {
//		        Users user = userOptional.get();
//		        int newStatus = (user.getIslock() == 0) ? 1 : 0;
//		        user.setIslock(newStatus);
//		        userService.saveUser(user);
//		    } else {
//		        throw new IllegalArgumentException("User not found: " + username);
//		    }
//	        return "redirect:/khk/userListPage/"; 
//	        		// 사용자 목록 페이지로 리디렉션
//	   
//
//	
	}

