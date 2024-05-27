package com.projectA1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projectA1.model.Review;
import com.projectA1.model.ReviewData;
import com.projectA1.model.User;
import com.projectA1.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {
	
	@Autowired
    private ReviewRepository reviewRepository;
	
		@Transactional
	    public void addReview(Review review) {
	        reviewRepository.save(review);
	    }

		//해당 유저, 리뷰에 해당하는 사람이 있으면 삭제
	    public void deleteReview(Long reviewId, Long userId) {
	    	System.out.println("서비스 부분여기 수행완료");
	    	User user = new User();
	    	user.setId(userId);
	        reviewRepository.deleteByIdAndUser(reviewId, user);
	    }
		
	    public void deleteReview(Long id) {
	        reviewRepository.deleteById(id);
	    }

	
	    public Review getReviewById(Long id) {
	        return reviewRepository.findById(id).orElse(null);
	    }

	
	    public void updateReview(Review review) {
	        reviewRepository.save(review);
	    }


	    public List<Review> getAllReviews(Long centerId) {
	        return reviewRepository.findAllByCenterId(centerId);
	    }


	    public List<Review> findByCenterId(Long id) {
	        return reviewRepository.findAllByCenterId(id);
	    }
	    
	    
	    public Page<Review> findByCenterId(Long centerId, Pageable pageable) {
	        return reviewRepository.findByCenterId(centerId, pageable);
	    }

	    // 사용자 ID에 해당하는 리뷰 삭제
	    @Transactional
	    public void deleteByUserId(User user) {
	        reviewRepository.deleteByUser(user);
	    }
}
