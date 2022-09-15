package com.example.DiningReviewApi.DiningReviews;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReviewScoreConverter implements AttributeConverter<ReviewScore, String> {

	@Override
	public String convertToDatabaseColumn(ReviewScore rvwScore) {
		if (rvwScore == null) {
			return null;
		}
		return rvwScore.getScore();
	}

	@Override
	public ReviewScore convertToEntityAttribute(String score) {
		if (score == null) {
			return null;
		}

		return Stream.of(ReviewScore.values()).filter(s -> s.getScore().equals(score)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
