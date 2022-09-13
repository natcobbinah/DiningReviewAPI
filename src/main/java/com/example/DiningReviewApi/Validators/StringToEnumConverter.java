package com.example.DiningReviewApi.Validators;

import org.springframework.core.convert.converter.Converter;

import com.example.DiningReviewApi.DiningReviews.Status;

public class StringToEnumConverter implements Converter<String, Status> {

	@Override
	public Status convert(String source) {
		return Status.valueOf(source.toUpperCase());
	}
	
	
}
