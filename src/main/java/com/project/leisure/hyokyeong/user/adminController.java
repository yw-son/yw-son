package com.project.leisure.hyokyeong.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.UserRole;
import com.project.leisure.taeyoung.user.Users;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class adminController {
	
	
	private final UserRepository userRepository;
	private final UserListService userService;
	
	
	@GetMapping("/adminMain")
	public String adminMain () {
		
		return "khk/adminMain";
	}
	
	@GetMapping("/userListPage")
	public String Userlist (Model model) {
        List<Users> users = this.userRepository.findAll();
        model.addAttribute("users",users);
		return "khk/userListPage";
	}
	
	
	@PostMapping("/users/{userId}/role")
	public void updateUserRole(@PathVariable("userId") Long userId, @RequestParam("role") UserRole role) {
        // DB에서 해당 userId의 사용자를 조회하여 회원 등급 변경 및 admin_code 업데이트
		
		System.out.println("============");
		System.out.println(role);
		System.out.println(userId);
		System.out.println("============");

        userService.updateUserRole(userId, role);
//        return ResponseEntity.ok("회원 등급이 변경되었습니다.");
    }
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

	
