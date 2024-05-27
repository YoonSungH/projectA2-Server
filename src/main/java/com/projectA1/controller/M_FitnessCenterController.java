package com.projectA1.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectA1.config.auth.PrincipalUser;
import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Owner;
import com.projectA1.model.Review;
import com.projectA1.model.User;
import com.projectA1.service.FitnessCenterService;
import com.projectA1.service.OwnerService;
import com.projectA1.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/m_centerManage/*")
@RequiredArgsConstructor
public class M_FitnessCenterController {

	private final FitnessCenterService fitnessCenterService;
	private final OwnerService ownerService;
	private final ReviewService reviewService;

	@GetMapping("/gymList")
    public ResponseEntity<?> getGymList() {
        System.out.println("서버 응답 완료");
        List<FitnessCenter> centerList = fitnessCenterService.findByCenter2();
       
        // FitnessCenter 목록이 비어 있는지 확인
        for(int i = 0 ; i < centerList.size(); i++) {
           centerList.get(i).setOwners(null);
        }
        return ResponseEntity.ok(centerList);//
    }
	

//	// center 등록 폼
//	@GetMapping("joinForm")
//	public ResponseEntity<String> CenterJoinForm() {
//		return ResponseEntity.ok("/center/centerJoin");
//	}
//
//
//
//	// 센터 등록(이미지파일때문에 길어짐)
//	@PostMapping("register")
//	public ResponseEntity<String> join(@AuthenticationPrincipal PrincipalUser principalUser, @RequestParam("name") String name,
//			@RequestParam("address") String address, @RequestParam("phoneNumber") String phoneNumber,
//			@RequestParam("dailyPassPrice") Long dailyPassPrice, @RequestParam("openTime") LocalTime openTime,
//			@RequestParam("closingTime") LocalTime closingTime, @RequestParam("image") MultipartFile image) {
//		try {
//			// 이미지 파일 저장
//			// UUID 사용 이름 중복 방지
//			UUID uuid = UUID.randomUUID();
//			String uploadDir = "src/main/resources/static/img"; // 이미지를 저장할 디렉토리
//			String fileName = uuid.toString() + "_" + image.getOriginalFilename();
//			System.out.println(fileName);
//			String fullPath = uploadDir + "/" + fileName; // 파일의 전체 경로
//
//			// 파일을 저장할 디렉토리 생성
//			Path uploadPath = Paths.get(uploadDir);
//			if (!Files.exists(uploadPath)) {
//				Files.createDirectories(uploadPath);
//			}
//
//			// 이미지 파일 저장
//			try (InputStream inputStream = image.getInputStream()) {
//				Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//			}
//
//			// FitnessCenter 객체 생성 및 속성 설정
//			FitnessCenter fitnessCenter = new FitnessCenter();
//			fitnessCenter.setName(name);
//			fitnessCenter.setAddress(address);
//			fitnessCenter.setPhoneNumber(phoneNumber);
//			fitnessCenter.setDailyPassPrice(dailyPassPrice);
//			fitnessCenter.setOpenTime(openTime);
//			fitnessCenter.setClosingTime(closingTime);
//			fitnessCenter.setImagePath(fileName);
//
//			// 센터 등록
//			fitnessCenterService.join(fitnessCenter);
//
//			// 현재 owner에 센터 아이디 등록
//			Owner owner = (Owner) principalUser.getUser();
//			ownerService.addFitnessCenterToOwner(owner, fitnessCenter);
//
//			return ResponseEntity.ok("success");
//		} catch (IOException e) {
//			e.printStackTrace();
//			return ResponseEntity.ok("error");
//		}
//	}
//
//	// 수정폼
//	@GetMapping("update")
//	public ResponseEntity<String> update(@AuthenticationPrincipal PrincipalUser principalUser, Model model) {
//		Owner owner = (Owner) principalUser.getUser();
//		Long id = owner.getFitnessCenter().getId();
//		model.addAttribute("center", fitnessCenterService.view(id));
//		return ResponseEntity.ok("/center/updateForm");
//	}
//
//
//	@PutMapping("update")
//	public ResponseEntity<String> updateFitnessCenter(@RequestParam("id") Long id, @RequestParam("name") String name,
//			@RequestParam("address") String address, @RequestParam("phoneNumber") String phoneNumber,
//			@RequestParam("dailyPassPrice") Long dailyPassPrice, @RequestParam("openTime") LocalTime openTime,
//			@RequestParam("closingTime") LocalTime closingTime, @RequestParam("image") MultipartFile image) throws IOException {
//
//		// 이미지 파일 저장
//		// UUID 사용 이름 중복 방지
//		UUID uuid = UUID.randomUUID();
//		String uploadDir = "src/main/resources/static/img"; // 이미지를 저장할 디렉토리
//		String fileName = uuid.toString() + "_" + image.getOriginalFilename();
//		String fullPath = uploadDir + "/" + fileName; // 파일의 전체 경로
//
//		// 파일을 저장할 디렉토리 생성
//		Path uploadPath = Paths.get(uploadDir);
//		if (!Files.exists(uploadPath)) {
//			Files.createDirectories(uploadPath);
//		}
//
//		// 이미지 파일 저장
//		try (InputStream inputStream = image.getInputStream()) {
//			Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//		}
//
//		// FitnessCenter 객체 생성 및 속성 설정
//		FitnessCenter fitnessCenter = new FitnessCenter();
//		fitnessCenter.setId(id); // 수정할 FitnessCenter의 ID 설정
//		fitnessCenter.setName(name);
//		fitnessCenter.setAddress(address);
//		fitnessCenter.setPhoneNumber(phoneNumber);
//		fitnessCenter.setDailyPassPrice(dailyPassPrice);
//		fitnessCenter.setOpenTime(openTime);
//		fitnessCenter.setClosingTime(closingTime);
//		fitnessCenter.setImagePath(fileName);
//
//		// Fitness Center 수정
//		fitnessCenterService.update(fitnessCenter);
//
//		return ResponseEntity.ok("success");
//	}
//
//	// 삭제
//	@GetMapping("delete")
//	public ResponseEntity<String> delete(@AuthenticationPrincipal PrincipalUser principalUser) {
//		// 고유 아이디로 삭제처리
//		Owner owner = (Owner) principalUser.getUser();
//		Long id = owner.getFitnessCenter().getId();
//
//		// 센터아이디 삭제
//		ownerService.clearCenterId(owner);
//		fitnessCenterService.deleteFitnessCenter(id);
//		return ResponseEntity.ok("redirect:/");
//	}
//
//	@GetMapping("/view/{id}")
//	public ResponseEntity<String> view(@PathVariable Long id, @RequestParam(defaultValue = "0") String page,
//			@RequestParam(defaultValue = "5") String size, Model model) {
//
//		// 페이지 번호와 페이지 크기의 유효성 검사
//		int pageNumber;
//		int pageSize;
//		try {
//			pageNumber = Integer.parseInt(page);
//			pageSize = Integer.parseInt(size);
//		} catch (NumberFormatException e) {
//			pageNumber = 0;
//			pageSize = 5;
//		}
//		if (pageNumber < 0) {
//			pageNumber = 0; // 페이지 번호가 음수인 경우 0으로 설정
//		}
//		if (pageSize <= 0) {
//			pageSize = 5; // 페이지 크기가 0 또는 음수인 경우 기본값인 5로 설정
//		}
//
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Review> reviewsPage = reviewService.findByCenterId(id, pageable);
//
//		double avg = 0.0;
//		int sum = 0;
//		List<Review> reviews = reviewsPage.getContent(); // 페이지에 해당하는 후기 목록 가져오기
//
//		for (Review review : reviews) {
//			sum += review.getRating();
//		}
//
//		if (!reviews.isEmpty()) {
//			avg = (double) sum / reviews.size();
//		}
//
//		int totalPages = reviewsPage.getTotalPages(); // 전체 페이지 수 가져오기
//
//		int startPage;
//		int endPage;
//		if (totalPages <= 3) {
//			startPage = 0;
//			endPage = totalPages - 1;
//		} else {
//			startPage = Math.max(0, pageNumber / 3 * 3);
//			endPage = Math.min(totalPages - 1, startPage + 2);
//			if (endPage - startPage < 2) { // 페이지가 부족한 경우 보정
//				startPage = Math.max(0, endPage - 2);
//			}
//		}
//
//		model.addAttribute("avg", avg);
//		model.addAttribute("reviews", reviews);
//		model.addAttribute("fitnessCenter", fitnessCenterService.view(id));
//		model.addAttribute("currentPage", pageNumber); // 수정된 부분
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//
//		return ResponseEntity.ok("center/gymview");
//	}
//    
//	// 전체보기
//	@GetMapping("gymlist")
//	public ResponseEntity<String> getAllFitnessCenters(Model model) {
//		//model.addAttribute("imagePath", imagePath);
//		model.addAttribute("fitnessCenters", fitnessCenterService.viewAll());
//		return ResponseEntity.ok("/center/gymlist");
//	}
}
