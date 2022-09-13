package com.example.DiningReviewApi.DataModels;

import lombok.Data;

@Data
public class RestaurantDataModel {

	private RestaurantAddress restaurantAddress;

	private Integer peanutAllergyScore;
	
	private Integer eggAllergyScore;
	
	private Integer dairyAllergyScore;

	private Integer overAllRestaurantScore;

}
