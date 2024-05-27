package com.projectA1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectA1.model.FitnessCenter;
import com.projectA1.model.User;
import com.projectA1.model.VisitCounting;
import com.projectA1.repository.VisitCountingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitCountingService {

	private final VisitCountingRepository visitCountingRepository;

	// userId로 해당 center 몇번 방문했는지
	public Long visitCounting(Long userId) {
		return visitCountingRepository.countByUserId(userId);
	}

	public void visit(VisitCounting visitCounting) {
		visitCountingRepository.save(visitCounting);
	}

	public VisitCounting findByUserIdCenterId(User user, FitnessCenter center) {
		// TODO Auto-generated method stub
		return visitCountingRepository.findByUserAndCenter(user, center);
	}
	
	public List<Object[]> findTop3VisitedCenters(Long userId) {
		return visitCountingRepository.findTop3VisitedCenters(userId);
	}
	
    // 사용자 ID에 해당하는 방문 기록 삭제
    @Transactional
    public void deleteByUserId(User user) {
        visitCountingRepository.deleteByUser(user);
    }

}
