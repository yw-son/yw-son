package com.project.leisure.hyokyeong.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.UserRole;
import com.project.leisure.taeyoung.user.Users;

@Service
public class UserListService {
    private final UserRepository userRepository;

    public UserListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUserRole(Long userId, UserRole role) {
        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setRole(role);
            updateAdminCode(user);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
    }

    private void updateAdminCode(Users user) {
        if (user.getRole() == UserRole.ADMIN) {
            user.setAdmin_code(1111);
        } else if (user.getRole() == UserRole.PARTNER) {
            user.setAdmin_code(3333);
        } else {
            user.setAdmin_code(0);
        }
    }
}
