package com.projectA1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "f_owner")
public class Owner extends Person {

	//@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne
	@JoinColumn(name = "center_id")
    private FitnessCenter fitnessCenter;

    private String businessRegistrationNumber; // 사업자 등록번호
}