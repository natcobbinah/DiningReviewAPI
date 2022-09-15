package com.example.DiningReviewApi.User;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name ="USERS")
public class User {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "display_name", unique = true)
	private String displayName;
	
	@Embedded
	private UserAddress userAddress;
	
	@Column(name = "interest_peanut_allergy")
	private boolean interestPeanutAllergies;
	
	@Column(name = "interest_egg_allergy")
	private boolean interestEggAllergies;
	
	@Column(name = "interest_diary_allergy")
	private boolean interestDiaryAllergies;
}
