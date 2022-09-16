package com.example.DiningReviewApi.DataModels;

import lombok.Data;

@Data
public class RestaurantDTO {

	private RestaurantAddress restaurantAddress;

	private Double peanutAllergyScore;
	
	private Double eggAllergyScore;
	
	private Double dairyAllergyScore;

	private Double overAllRestaurantScore;

}
