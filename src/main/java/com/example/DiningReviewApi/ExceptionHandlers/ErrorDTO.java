package com.example.DiningReviewApi.ExceptionHandlers;

import lombok.Data;

@Data
public class ErrorDTO {

	public String status;
	public String message;
	public String time;
}
