package com.example.DiningReviewApi.DiningReviews;

import java.util.Optional;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DINING_REVIEW")
@Data
@NoArgsConstructor
public class DiningReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diningReviewId;

	@Column(name = "reviewer_name", unique = true)
	private String name;

	@ManyToOne(optional=false) 
	@JoinColumn(name = "restaurant_Id", nullable = false)
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
	
	@Enumerated()
	@Column(name = "review_status")
	private Status status;

}
