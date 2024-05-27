package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	
	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

	//유저삭제 => email기준
	void deleteByEmail(String email);
}