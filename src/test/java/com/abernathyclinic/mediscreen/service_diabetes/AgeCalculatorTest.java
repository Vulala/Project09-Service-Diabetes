package com.abernathyclinic.mediscreen.service_diabetes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abernathyclinic.mediscreen.service_diabetes.service.AgeCalculator;

class AgeCalculatorTest {

	@Autowired
	private AgeCalculator ageCalculator = new AgeCalculator();

	@DisplayName("Calculate the age of a patient using the birthdate.")
	@Test
	void givenGettingTheAgeOfAPatient_whenGetAgeFromBirthDate_ThenItReturnTheAgeOfThePatient() {
		String birthdate = "01/01/2020";
		int result = ageCalculator.getAgeFromBirthDate(birthdate);

		assertEquals(1, result);
	}
}
