package com.example.DiningReviewApi.DiningReviews;

public enum ReviewScore {
	ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5");

	private String score;
	
	ReviewScore(String score) {
		this.score = score;
	}
	
	public String getScore() {
		return score;
	}
}
