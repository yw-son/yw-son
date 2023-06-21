package com.project.leisure.hyokyeong.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.UserRole;
import com.project.leisure.taeyoung.user.Users;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class UserListController {
	
	
	private final UserRepository userRepository;
	private final UserListService userService;
	
	@GetMapping("/khk/userListPage")
	public String Userlist (Model model) {
        List<Users> users = this.userRepository.findAll();
        model.addAttribute("users",users);
		return "khk/userListPage";
	}
	
	
	@PostMapping("/users/{userId}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long userId, @RequestBody UserRole role) {
        // DB에서 해당 userId의 사용자를 조회하여 회원 등급 변경 및 admin_code 업데이트
        userService.updateUserRole(userId, role);
        return ResponseEntity.ok("회원 등급이 변경되었습니다.");
    }
}
