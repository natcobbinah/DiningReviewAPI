package com.example.DiningReviewApi.Validators;

public class NameValidator {

	public static String ValidateName(String name) {

			String strippedNameofSpecialChars = "!@#$%^&*()-';,./?><+" + name;
			strippedNameofSpecialChars = strippedNameofSpecialChars.replaceAll("\\W+", "");

			return strippedNameofSpecialChars;
		
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * String myName = "@natha798sdf}+09(";
	 * 
	 * String validatedName = NameValidator.ValidateName(myName);
	 * 
	 * System.out.println(validatedName); }
	 */
}
