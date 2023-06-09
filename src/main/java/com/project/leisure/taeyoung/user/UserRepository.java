package com.project.leisure.taeyoung.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;

import com.nimbusds.oauth2.sdk.Role;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByusername(String username);
	
	List<Users> findByUsername(String username);

	Optional<Users> findByEmail(String email);
	
    @Query("SELECT u FROM Users u WHERE u.username = :username AND u.email = :email")
    Optional<Users> findByUsernameAndEmail(@Param("username") String username, @Param("email") String email);
    
    List<Users> findByNickname(String nickname);
    
    
}
