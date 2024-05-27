package com.projectA1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{
	boolean existsByEmail(String email);
	
	Owner findByEmail(String email);
	//이 부분 PrincipalDetail.java 때문에 필요
//	Owner findByOwnername(String ownerName);

	void save(Optional<Owner> owner);

}
