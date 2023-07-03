package com.project.leisure.taeyoung.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//회원DB 접근 레퍼지토리
public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByusername(String username);

	List<Users> findByUsername(String username);

	Optional<Users> findByEmail(String email);

	@Query("SELECT u FROM Users u WHERE u.username = :username AND u.email = :email")
	Optional<Users> findByUsernameAndEmail(@Param("username") String username, @Param("email") String email);

	List<Users> findByNickname(String nickname);
	
	Optional<Users> findById(Long id);
	

	/////////////////효경 수정//////////////////
	Users save(Users user); // saveUser 메서드 추가
	

    // 회원 상태(islock)를 조회하는 메서드
	@Query("SELECT u.islock FROM Users u WHERE u.username = :username")
    int findIslockByUsername(@Param("username") String username);
	
	default void saveUser(Users user) {
		save(user);
		
	}
}

