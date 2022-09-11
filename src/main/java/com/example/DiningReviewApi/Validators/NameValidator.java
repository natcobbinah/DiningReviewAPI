package com.example.DiningReviewApi.Validators;

import org.apache.commons.lang3.StringUtils;

public class NameValidator {

	public static String ValidateName(String name) {

		String strippedNameofSpecialChars = "!@#$%^&*()-';,./?><+" + name;
		strippedNameofSpecialChars = strippedNameofSpecialChars.replaceAll("\\W+", "");

		return strippedNameofSpecialChars;

	}

	public static boolean isNameValid(String userName) {

		if (StringUtils.isAlphanumeric(userName)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		/*
		 * String myName = "@natha798sdf}+09(";
		 * 
		 * String validatedName = NameValidator.ValidateName(myName);
		 * 
		 * System.out.println(validatedName);
		 */

		/*
		 * String strippedNameofSpecialChars = "!@#$%^&*()-';,./?><+{}"; String myName =
		 * "Nzthanie{}"; if (StringUtils.containsAny(myName,
		 * strippedNameofSpecialChars)) {
		 * System.out.println("Invalid characters in Name"); } else {
		 * System.out.println(myName); }
		 */

	}
}
