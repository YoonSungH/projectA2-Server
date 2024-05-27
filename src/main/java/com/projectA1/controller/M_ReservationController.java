package com.projectA1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Reservation;
import com.projectA1.model.User;
import com.projectA1.model.VisitCounting;
import com.projectA1.service.ReservationService;
import com.projectA1.service.VisitCountingService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping("/m_reservation/*")
@RequiredArgsConstructor
public class M_ReservationController {

	private final ReservationService reservationService;
	private final VisitCountingService visitCountingService; // 방문횟수 저장

	// 예약 등록
	@PostMapping("create")
	public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
		reservationService.create(reservation);
		return ResponseEntity.ok("success");
	}

	// 예약 리스트 클라이언트 반환
	@PostMapping("list")
	public ResponseEntity<Result<Reservation>> getUserReservations(@RequestParam("userId") Long userId) {
		// 당일 날짜 카운팅
		int count = 0;
		List<Reservation> reservations = reservationService.findByUserId(userId);
		for (Reservation reservation : reservations) {
			reservation.getCenter().setOwners(null);
		}

		// 당일예약 정보, 카운팅만 보냄
		Result<Reservation> result = new Result<>(reservations, count);
		return ResponseEntity.ok().body(result);
	}

	// 이너클래스 => 리스트 보내기 위함(get,set필요)
	@Getter
	@Setter
	static class Result<T> {
		private T data;
		private int count;

		@SuppressWarnings("unchecked")
		public Result(List<Reservation> data, int count) {
			this.data = (T) data;
			this.count = count;
		}
	}

	// 예약 취소
	@PostMapping("delete")
	public ResponseEntity<String> delete(@RequestParam Long reservationId) {
		reservationService.delete(reservationId);
		return ResponseEntity.ok("success");
	}

	// 예약 사용함(임시로 만들어둔 버튼)
	@PostMapping("used")
	public ResponseEntity<String> used(@RequestParam Long reservationId) {
		Optional<Reservation> reservation = reservationService.findReservation(reservationId);
		User user = reservation.get().getUser();
		FitnessCenter center = reservation.get().getCenter();

		// 유저와 센터 기록 존재 여부 파악위함
		VisitCounting visitCounting = new VisitCounting(user, center);

		// 방문기록
		visitCountingService.visit(visitCounting);
		// 예약기록 삭제
		System.out.println(reservationId);
		reservationService.delete(reservationId);
		System.out.println("수행완료");
		return ResponseEntity.ok("success");
	}

	/*
	 * @PostMapping("list") public ResponseEntity<String>
	 * getUserReservations(@RequestParam("userId") Long userId) throws
	 * JsonProcessingException { // userId를 이용하여 해당 사용자의 예약 리스트를 조회하고 반환하는 코드를 작성해야
	 * 합니다. List<Reservation> reservations =
	 * reservationService.findByUserId(userId); // 예약 리스트를 조회하는 코드 작성
	 * System.out.println(reservations.getClass()); for(int i = 0; i <
	 * reservations.size(); i++) {
	 * System.out.println(reservations.get(i).getCenter().getName()); }
	 * 
	 * // Java 객체를 JSON 문자열로 변환 ObjectMapper objectMapper = new ObjectMapper();
	 * String jsonReservations = objectMapper.writeValueAsString(reservations);
	 * 
	 * // JSON 문자열을 ResponseEntity에 담아 반환 return ResponseEntity.ok()
	 * .contentType(MediaType.APPLICATION_JSON) .body(jsonReservations); }
	 */

	// 예약지점 상세보기
//	@GetMapping("view/{reservationId}")
//	public ResponseEntity<String> view(@PathVariable Long reservationId, Model model) {
//		model.addAttribute("reservation", reservationService.findReservation(reservationId));
//		return ResponseEntity.ok("redirect:/reservation/view");
//	}

	// 예약 수정폼(모달로 처리)
//	@GetMapping("update/{reservationId}")
//	public String update(@PathVariable Long reservationId, Model model) {
//		Optional<Reservation> reservation = reservationService.findReservation(reservationId);	
//		model.addAttribute("reservation",reservation.get());
//		return "/reservation/updateForm";
//	}
//	
//	//예약수정
//	@PutMapping("update/{reservationId}")
//	@ResponseBody
//	public String update(@PathVariable Long reservationId, @RequestParam Date newReservationTime) {
//		Optional<Reservation> reservation = reservationService.findReservation(reservationId);
//		
//		reservation.get().setReservationTime(newReservationTime);
//		reservationService.create(reservation.get());
//		return "success";
//	}

}
