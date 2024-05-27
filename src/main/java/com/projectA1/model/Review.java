package com.projectA1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name="review_content")
@Getter @Setter
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user; //user id
    
    @ManyToOne
    @JoinColumn(name = "centerId", nullable = false)
    private FitnessCenter center; //센터번호

    private Integer rating; //평점
    private String reviewText; // 후기
}