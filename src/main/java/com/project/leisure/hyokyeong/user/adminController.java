package com.project.leisure.hyokyeong.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class adminController {

	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final UserListService userListService;

	@GetMapping("/adminMain")
	public String adminMain() {
		System.out.println("메인페이지로 이동");
		return "khk/adminMain";
	}

	@GetMapping("/authorityPage")
	public String authorityPage(Model model) {
		userListService.userList(model);
		return "khk/authorityPage";
	}

	@GetMapping("/userListPage")
	public String userListPage(Model model) {
		userListService.userList(model);
		return "khk/userListPage";
	}

	@PostMapping("/{id}/adminRole")
	public String updateUserRole(@PathVariable("id") Long id, @RequestParam("role") UserRole role, Model model) {
		try {
			userListService.updateUserRole(id, role); // 서비스를 통해 DB 업데이트 수행
			List<Users> userList = userListService.userList(model); // 변경된 회원 목록을 조회
			model.addAttribute("users", userList); // 변경된 회원 목록을 모델에 추가
			return "khk/authorityPage"; // 회원 목록 페이지로 이동
		} catch (IllegalArgumentException e) {
			return "redirect:/khk/adminMain"; // 예외 발생 시 관리자 메인 페이지로 이동
		}
	}

	@PostMapping("/toggle-account")
	public ResponseEntity<Map<String, Integer>> toggleAccount(@RequestParam(name = "username", value = "") String username,
	        HttpServletRequest request) {
	    try {
	        int newStatus = userListService.toggleAccountStatus(username); // 계정 상태 토글 메서드 호출

	        // 토글된 사용자 및 새로운 상태를 Map으로 저장
	        Map<String, Integer> response = new HashMap<>();
	        response.put("newStatus", newStatus);

	        HttpSession session = request.getSession();
	        session.setAttribute("toggledUser", username); // 토글된 사용자를 세션에 저장

	        return ResponseEntity.ok(response);
	    } catch (IllegalArgumentException e) {
	        // 예외 처리
	        return ResponseEntity.badRequest().build();
	    }
	}


//	 @Autowired  
//	 @PostMapping("/toggle-account")
//	 public String toggleAccount(@RequestParam(name = "username", value = "") String username) {
//		    Optional<Users> userOptional = userListService.getUserByUsername(username);
//		    if (userOptional.isPresent()) {
//		        Users user = userOptional.get();
//		        int newStatus = (user.getIslock() == 0) ? 1 : 0;
//		        user.setIslock(newStatus);
//		        userListService.saveUser(user);
//		    } else {
//		        throw new IllegalArgumentException("User not found: " + username);
//		    }
//	        return "redirect:/admin/userListPage/"; 
//	        		// 사용자 목록 페이지로 리디렉션
//	   
//
//	
//	}

//////////////////////////파트너 관리 컨트롤러///////////////////////////////////	

	@GetMapping("/partnerListPage")
	public String partnerlist(Model model) {
		userListService.userList(model);
		return "khk/userListPage";
	}

}
