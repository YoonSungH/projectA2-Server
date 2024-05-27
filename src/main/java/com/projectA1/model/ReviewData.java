package com.projectA1.model;

public class ReviewData {
	private Long id;
    private Long userId;
    private Long centerId;
    private Integer rating;
    private String reviewText;
    private String userName;

    // 생성자, 게터, 세터 등 필요한 메서드를 추가할 수 있음

    // 생성자
    public ReviewData(Long id, Long userId, Long centerId, Integer rating, String reviewText, String userName) {
    	this.id = id;
    	this.userId = userId;
    	this.centerId = centerId;
    	this.rating = rating;
    	this.reviewText = reviewText;
    	this.userName = userName;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// 게터와 세터 메서드
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
