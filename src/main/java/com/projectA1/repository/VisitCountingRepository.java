package com.projectA1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectA1.model.FitnessCenter;
import com.projectA1.model.User;
import com.projectA1.model.VisitCounting;

public interface VisitCountingRepository extends JpaRepository<VisitCounting, Long> {

	// userIdf를 기반으로 방문 횟수를 카운트하는 메서드
	Long countByUserId(Long userId);

	VisitCounting findByUserAndCenter(User user, FitnessCenter center);

	// top3 출력
//	@Query("SELECT vc FROM VisitCounting vc WHERE vc.id = :userId ORDER BY vc.visitCount ASC")
//	List<VisitCounting> findTop3VisitedCenters(@Param("userId") Long userId, Pageable pageable);

	//top3
	@Query("SELECT vc.center.id, COUNT(vc) as visitCount " +
		       "FROM VisitCounting vc " +
		       "WHERE vc.user.id = :userId " +
		       "GROUP BY vc.center.id " +
		       "ORDER BY visitCount DESC " +
		       "LIMIT 3")
		List<Object[]> findTop3VisitedCenters(Long userId);

	//해당 id에 있는 예약 전부 삭제
	void deleteAllById(Long id);

	void deleteByUserId(Long id);
	
    // 사용자에 대한 방문 기록 삭제
    void deleteByUser(User user);


}
