package com.example.DiningReviewApi.DataModels;

import lombok.Data;

@Data
public class UserDataModel {

	private String displayName;
	
	private UserAddressDataModel userAddressDataModel;
	
	private boolean interestPeanutAllergies;
	
	private boolean interestEggAllergies;
	
	private boolean interestDiaryAllergies;
}
