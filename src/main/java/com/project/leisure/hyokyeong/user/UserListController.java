package com.project.leisure.hyokyeong.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.UserService;
import com.project.leisure.taeyoung.user.Users;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class UserListController {
	
	
	private final UserRepository userRepository;
	
	@GetMapping("/khk/userListPage")
	public String Userlist (Model model) {
        List<Users> users = this.userRepository.findAll();
        model.addAttribute("users",users);
		return "khk/userListPage";
	}
}
