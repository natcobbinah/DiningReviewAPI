package com.example.DiningReviewApi.DataModels;

import lombok.Data;

@Data
public class RestaurantDataModel {

	private RestaurantAddress restaurantAddress;

	private Double peanutAllergyScore;
	
	private Double eggAllergyScore;
	
	private Double dairyAllergyScore;

	private Double overAllRestaurantScore;

}
