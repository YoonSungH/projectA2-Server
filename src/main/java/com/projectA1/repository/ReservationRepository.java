package com.projectA1.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Reservation;
import com.projectA1.model.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	//유저 예약된 정보 다 표시
	List<Reservation> findAllByUserId(Long id);
	
	List<Reservation> findByCenterId(Long centerId);

	void deleteByUser(User user);
}
