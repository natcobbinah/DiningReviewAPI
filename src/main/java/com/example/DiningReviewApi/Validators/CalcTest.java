package com.example.DiningReviewApi.Validators;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class CalcTest {

	private int result;
	
	public static void main(String[] args) {
		
		List<Integer> values = new LinkedList<>();
		values.add(2);
		values.add(5);
		values.add(7);
		
		Integer sum = values.stream().reduce(0, Integer::sum);
		
		System.out.println("Average = " + Double.valueOf(sum)/ Double.valueOf(values.size()));
	}

}
