package com.abernathyclinic.mediscreen.service_diabetes.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

/**
 * Service used to calculate the age of a patient.
 */
@Service
public class AgeCalculator {

	/**
	 * Calculate the age of a patient from the date of birth. <br>
	 */
	public int getAgeFromBirthDate(String birthDate) {
		int age;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Equal to the format from the POJO/Front-end
		LocalDate localDate = LocalDate.now(); // Get the current LocalDate
		LocalDate localDateBirthDate = LocalDate.parse(birthDate, dateTimeFormatter);
		// Format the birthDate to a LocalDate according to the dateTimeFormatter

		age = localDate.getYear() - localDateBirthDate.getYear();
		return age;
	}

}
