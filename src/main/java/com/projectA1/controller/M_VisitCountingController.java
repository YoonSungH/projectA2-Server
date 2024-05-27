package com.projectA1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectA1.model.User;
import com.projectA1.service.UserService;
import com.projectA1.service.VisitCountingService;

import lombok.RequiredArgsConstructor;

//사실 이게 필요한지는 생각해 봐야 할 부분
@RestController
@RequestMapping("/m_visit/")
@RequiredArgsConstructor
public class M_VisitCountingController {
	
	private final VisitCountingService visitCountingService;
	private final UserService userService;

	@PostMapping("/myCounting")
	public ResponseEntity<Long> getMyCounting(@RequestParam Long userId) {
	    // visitCountingService.visitCounting(userId)를 호출하고 결과를 기다림
	    Long count = (long) visitCountingService.visitCounting(userId);
	    System.out.println(count);
	    // ResponseEntity로 결과를 감싸서 반환
	    return ResponseEntity.ok(count);
	}

	
}
