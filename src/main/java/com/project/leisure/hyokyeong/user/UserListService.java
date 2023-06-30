package com.project.leisure.hyokyeong.user;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.UserRole;
import com.project.leisure.taeyoung.user.Users;

@Service
@EnableScheduling
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
			user.setPartner_code(0);
			user.setAdmin_code(1111);
			userRepository.save(user);
		} else if (user.getRole() == UserRole.PARTNER) {
			user.setAdmin_code(0);
			user.setPartner_code(3333);
			userRepository.save(user);
		} else {
			user.setAdmin_code(0);
			user.setPartner_code(0);
			userRepository.save(user);
		}
	}

	public int toggleAccountStatus(String username) {
		Optional<Users> userOptional = getUserByUsername(username);
		if (userOptional.isPresent()) {
			Users user = userOptional.get();
			int currentStatus = user.getIslock();
			int newStatus = (currentStatus == 0) ? 1 : 0; // 현재 상태와 반대로 토글
			user.setIslock(newStatus);

			if (newStatus == 1) {
				// 계정이 잠금 상태로 변경되었을 때
				LocalDateTime lockTime = LocalDateTime.now(); // 현재 시간을 잠금 시간으로 설정
				user.setLockTime(lockTime);
			} else {
				// 계정이 잠금 해제 상태로 변경되었을 때
				user.setLockTime(null); // 잠금 시간 초기화
			}

			saveUser(user);
			return newStatus;
		} else {
			// 사용자가 존재하지 않을 경우 예외 처리
			throw new IllegalArgumentException("User not found: " + username);
		}
	}

	public Optional<Users> getUserByUsername(String username) {
		List<Users> userList = userRepository.findByUsername(username);

		if (!userList.isEmpty()) {
			return Optional.of(userList.get(0));
		} else {
			return Optional.empty();
		}
	}

	public void saveUser(Users user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	public List<Users> userList(Model model) {
		List<Users> users = this.userRepository.findAll();
		model.addAttribute("users", users);
		return users;
	}

	@Scheduled(fixedDelay = 30000)
	public void toggleAccountStatusScheduled() {
		System.out.println("Scheduled task: Toggle account status");

		List<Users> users = userRepository.findAll();
		for (Users user : users) {
			if (user.getIslock() == 1) {
				// 사용자의 계정이 정지 상태일 경우
				LocalDateTime lockTime = user.getLockTime(); // 잠금 시간 가져오기
				LocalDateTime unlockTime = lockTime.plusMinutes(1); // 1분 후에 잠금 해제 시간 계산

				LocalDateTime now = LocalDateTime.now();
				if (now.isAfter(unlockTime)) {
					// 현재 시간이 잠금 해제 시간을 지났을 경우
					int newStatus = toggleAccountStatus(user.getUsername());
					System.out.println(
							"Account status toggled for user: " + user.getUsername() + ", New status: " + newStatus);
				} else {
					// 잠금 해제 시간을 아직 지나지 않았을 경우
					TimerTask task = new TimerTask() {
						@Override
						public void run() {
							int newStatus = toggleAccountStatus(user.getUsername());
							System.out.println("Account status toggled for user: " + user.getUsername()
									+ ", New status: " + newStatus);
						}
					};

					long delay = ChronoUnit.MILLIS.between(now, unlockTime); // 잠금 해제까지 남은 시간 계산
					Timer timer = new Timer();
					timer.schedule(task, delay); // 남은 시간 후에 실행

					// 계정 잠금 시간 업데이트
					user.setLockTime(unlockTime);
					userRepository.save(user);
				}
			}
		}
	}

}
