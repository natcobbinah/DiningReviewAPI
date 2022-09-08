package com.example.DiningReviewApi.DiningReviews;

public enum Status {
	ACCEPTED("A"), PENDING("P"), REJECTED("R");
	
	private String code;
	
	private Status(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
