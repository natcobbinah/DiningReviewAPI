package com.example.DiningReviewApi.DataModels;

import com.example.DiningReviewApi.DiningReviews.ReviewScore;
import com.example.DiningReviewApi.DiningReviews.Status;
import com.example.DiningReviewApi.Restaurant.Restaurant;

import lombok.Data;

@Data
public class DiningReviewDataModel {

	private String reviewerName;
	private Restaurant restaurant;
	private ReviewScore peanutAllergyScore;
	private ReviewScore eggAllergyScore;
	private ReviewScore dairyAllergyScore;
	private String commentary;
	private Status status;
}
