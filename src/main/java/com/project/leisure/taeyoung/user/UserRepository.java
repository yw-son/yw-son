package com.project.leisure.taeyoung.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByusername(String username);
	
	List<Users> findByUsername(String username);

	Optional<Users> findByEmail(String email);
	
    @Query("SELECT u FROM Users u WHERE u.username = :username AND u.email = :email")
    Optional<Users> findByUsernameAndEmail(@Param("username") String username, @Param("email") String email);
}
