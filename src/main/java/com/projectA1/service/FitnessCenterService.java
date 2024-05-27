package com.projectA1.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projectA1.model.FitnessCenter;
import com.projectA1.repository.FitnessCenterRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FitnessCenterService {

	private final FitnessCenterRepository fitnessCenterRepository;

	public List<FitnessCenter> findByCenter2() {
		return fitnessCenterRepository.findAll();
	}

	public Optional<FitnessCenter> findByCenter(Long centerId) {
		// TODO Auto-generated method stub
		return fitnessCenterRepository.findById(centerId);
	}
//	// 오너 id 이용, 센터이름 찾기
//	public String findByCenterName(Long id) {
//		FitnessCenter center = fitnessCenterRepository.findById(id).get();
//		String centerName = center.getName();
//
//		return centerName;
//	}
//
//	// 추가
//	public void join(FitnessCenter fitnesscenter) {
//		fitnessCenterRepository.save(fitnesscenter);
//	}
//
//	// 상세보기
//	@Transactional
//	public FitnessCenter view(Long id) {
//		FitnessCenter fitnessCenter = fitnessCenterRepository.findById(id).get();
//		return fitnessCenter;
//	}
//
//	// 전체보기
//	public List<FitnessCenter> viewAll() {
//		return fitnessCenterRepository.findAll();
//	}
//
//	// 수정 => 주소, 전화번호, 일일권, 이미지 수정가능
////	@Transactional
////	public void update(FitnessCenter fitnesscenter) {
////		FitnessCenter f = fitnessCenterRepository.findById(fitnesscenter.getId()).get();
////		f.setAddress(fitnesscenter.getAddress());
////		f.setPhoneNumber(fitnesscenter.getPhoneNumber());
////		f.setOpenTime(fitnesscenter.getOpenTime());
////		f.setClosingTime(fitnesscenter.getClosingTime());
////		f.setImagePath(fitnesscenter.getImagePath());
////	}
//
//	// 수정
//	@Transactional
//	public void update(FitnessCenter fitnesscenter) {
//		// 기존 FitnessCenter 가져오기
//		FitnessCenter f = fitnessCenterRepository.findById(fitnesscenter.getId())
//				.orElseThrow(() -> new IllegalArgumentException("Fitness Center를 찾을 수 없습니다: " + fitnesscenter.getId()));
//
//		// 기존 이미지 파일 삭제
//		deleteImageFile(f.getImagePath());
//
//		// 속성 업데이트
//		f.setId(fitnesscenter.getId());
//		f.setName(fitnesscenter.getName());
//		f.setAddress(fitnesscenter.getAddress());
//		f.setPhoneNumber(fitnesscenter.getPhoneNumber());
//		f.setDailyPassPrice(fitnesscenter.getDailyPassPrice());
//		f.setOpenTime(fitnesscenter.getOpenTime());
//		f.setClosingTime(fitnesscenter.getClosingTime());
//		f.setImagePath(fitnesscenter.getImagePath());
//	}
//
//	private void deleteImageFile(String imagePath) {
//		String uploadDir = "src/main/resources/static/img"; // 이미지를 저장한 디렉토리 경로
//		String fullPath = uploadDir + "/" + imagePath; // 기존 이미지 파일의 전체 경로
//
//		// 파일 삭제
//		Path filePath = Paths.get(fullPath);
//		try {
//			Files.deleteIfExists(filePath);
//		} catch (IOException e) {
//			e.printStackTrace();
//			// 파일 삭제 실패 처리
//		}
//	}
//
//	// 삭제
//	@Transactional
//	public void deleteFitnessCenter(Long id) {
//		fitnessCenterRepository.deleteById(id);
//	}
//

}
