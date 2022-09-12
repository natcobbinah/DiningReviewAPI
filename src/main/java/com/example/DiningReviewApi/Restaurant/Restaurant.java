package com.example.DiningReviewApi.Restaurant;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.DiningReviewApi.DiningReviews.DiningReview;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RESTAURANT")
@Data
@NoArgsConstructor
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurant_Id;

	@Embedded
	private RestaurantAddress restaurantAddress;

	@Column(name = "peanut_allergy_score")
	private Integer peanutAllergyScore;

	@Column(name = "egg_allergy_score")
	private Integer eggAllergyScore;

	@Column(name = "diary_allergy_score")
	private Integer dairyAllergyScore;

	@Column(name = "over_all_restaurant_score")
	private Integer overAllRestaurantScore;

	// @JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.EAGER)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "diningReviewId")
	private List<DiningReview> diningReview;

}
