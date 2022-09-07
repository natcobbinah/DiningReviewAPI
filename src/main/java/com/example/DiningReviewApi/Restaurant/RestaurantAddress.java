package com.example.DiningReviewApi.Restaurant;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class RestaurantAddress {
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "name")
	private String name;
}
