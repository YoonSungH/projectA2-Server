// VisitCounting.java
package com.projectA1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//VisitCount.java
@Entity
@Data
public class VisitCounting {

	public VisitCounting(User user, FitnessCenter center) {
		this.user = user;
		this.center = center;
	}
	public VisitCounting() {
	    // 기본 생성자 내용
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private FitnessCenter center;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
