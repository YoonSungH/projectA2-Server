// FitnessCenter.java
package com.projectA1.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

//FitnessCenter.java
/*@Entity(name = "f_center")
@Getter
@Setter
public class FitnessCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private Long dailyPassPrice;

	
	 * @DateTimeFormat(pattern = "HH:mm") private LocalTime openTime;
	 * 
	 * @DateTimeFormat(pattern = "HH:mm") private LocalTime closingTime;
	 

    @JsonFormat(pattern = "HH:mm")
    private LocalTime openTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime closingTime;
    // 이미지 파일 경로 저장 변수 추가
    private String imagePath; // 예: "/static/img/filename.jpg"

    // 수정된 부분: Owner와의 관계 설정 ==> (CascadeType.ALL) ->센터 삭제시, 오너도 삭제
    @OneToMany(mappedBy = "fitnessCenter", cascade = CascadeType.PERSIST)
    private List<Owner> owners; // 이 FitnessCenter를 소유한 Owner 목록

}*/

@Entity(name = "f_center")
@Getter
@Setter
public class FitnessCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private Long dailyPassPrice;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingTime;

    private String imagePath;

    @JsonFormat
    @OneToMany(mappedBy = "fitnessCenter", cascade = CascadeType.PERSIST)
    private List<Owner> owners;
}

