package com.projectA1.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.projectA1.model.Reservation;
import com.projectA1.model.User;
import com.projectA1.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	
	//예약 생성
	public void create(Reservation reservation) {
		reservationRepository.save(reservation);
	}
	
	//예약자 전체보기
	public List<Reservation>viewAll(){
		return reservationRepository.findAll();
	}
	//예약 수정
//	public void update(Reservation reservation) {
//		reservationRepository.
//	}
	
	//예약 삭제
	public void delete(Long reservationId) {
		reservationRepository.deleteById(reservationId);
	}

    //유저의 예약확인
	public List<Reservation> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findAllByUserId(id);
	}
	
	
	//오너의 예약자 확인
	public List<Reservation> findByCenterId(Long centerId) {
        return reservationRepository.findByCenterId(centerId);
    }

	
	//예약정보 찾기
	public Optional<Reservation> findReservation(Long reservationId) {
		// TODO Auto-generated method stub
		return reservationRepository.findById(reservationId);
	}

	public void deleteByUserId(User user) {
		reservationRepository.deleteByUser(user);
		
	}

}
