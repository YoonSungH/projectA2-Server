package com.projectA1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Review;
import com.projectA1.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
	
	List<Review> findAllByUserId(Long userId);
	
	List<Review> findAllByCenterId(Long centerId);
	//리뷰 전체가져오기
	List<Review> findByCenterId(Long id);
	
	Page<Review> findByCenterId(Long centerId, Pageable pageable);

	//해당유저 테이블 정보 삭제
	void deleteAllById(Long id);
	
    // 사용자에 대한 리뷰 삭제
    void deleteByUser(User user);
    
    // 해당 사용자의 해당 리뷰 삭제
    @Modifying
    @Transactional
    @Query("DELETE FROM review_content r WHERE r.id = :reviewId AND r.user = :user")
    void deleteByIdAndUser(Long reviewId, User user);
	
}
