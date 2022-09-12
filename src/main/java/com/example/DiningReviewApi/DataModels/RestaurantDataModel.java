package com.example.DiningReviewApi.DataModels;

import java.util.List;

import com.example.DiningReviewApi.DiningReviews.DiningReview;
import lombok.Data;

@Data
public class RestaurantDataModel {

	private RestaurantAddress restaurantAddress;

	private Integer peanutAllergyScore;
	
	private Integer eggAllergyScore;
	
	private Integer dairyAllergyScore;

	private Integer overAllRestaurantScore;

	private List<DiningReview> diningReview;
}
