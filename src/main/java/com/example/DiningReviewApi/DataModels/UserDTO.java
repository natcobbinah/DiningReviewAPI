package com.example.DiningReviewApi.DataModels;

import lombok.Data;

@Data
public class UserDTO {

	private String displayName;
	
	private UserAddress userAddress;
	
	private boolean interestPeanutAllergies;
	
	private boolean interestEggAllergies;
	
	private boolean interestDiaryAllergies;
}
