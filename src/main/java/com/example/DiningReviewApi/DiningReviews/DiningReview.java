package com.example.DiningReviewApi.DiningReviews;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.DiningReviewApi.Restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DINING_REVIEW")
@Getter
@Setter
@NoArgsConstructor
public class DiningReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diningReviewId;

	@Column(name = "reviewer_name")
	private String reviewerName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "restaurant_Id", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "restaurant_Id")
	@JsonIgnore
	private Restaurant restaurant;

	@Enumerated()
	@Column(name = "peanut_review_score")
	private ReviewScore peanutAllergyScore;

	@Enumerated()
	@Column(name = "egg_review_score")
	private ReviewScore eggAllergyScore;

	@Enumerated()
	@Column(name = "diary_review_score")
	private ReviewScore dairyAllergyScore;

	@Column(name = "commentary")
	private String commentary;

	// using attribute converter to autoApply enumRules
	@Column(name = "review_status")
	private Status status;

	// Implemented this custom toString() to see the error Thrown when @Data
	// annotation is used
	// this is because it causes a recursive toString() call on restaurant and back
	// to diningReviewEntity
	// So uncommenting the toString() and getting [Restaurant] directly throws the
	// stackOverFlowError,but getting
	// a specific field from [Restaurant] entity works fine

	// public String toString() {
	// return "DiningReview = [reviewId" + diningReviewId + " : ReviewerName" +
	// reviewerName + " : RestaurantID" + restaurant.getRestaurant_Id() + " :
	// PeanutAllergyScore" + peanutAllergyScore +" "
	// + " ]";
	// }

}
