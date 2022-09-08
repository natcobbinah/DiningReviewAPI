package com.example.DiningReviewApi.DiningReviews;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status status) {
		if (status == null) {
			return null;
		}
		return status.getCode();
	}

	@Override
	public Status convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}

		return Stream.of(Status.values()).filter(s -> s.getCode().equals(code)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
