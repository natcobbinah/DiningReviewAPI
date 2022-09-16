package com.example.DiningReviewApi.DataModels;

import com.example.DiningReviewApi.DiningReviews.ReviewScore;
import com.example.DiningReviewApi.DiningReviews.Status;
import lombok.Data;

@Data
public class DiningReviewDTO {

	private String reviewerName;
	
	private Long restaurantId;
	
	private ReviewScore peanutAllergyScore;
	
	private ReviewScore eggAllergyScore;
	
	private ReviewScore dairyAllergyScore;
	
	private String commentary;
}
