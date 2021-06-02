package com.abernathyclinic.mediscreen.service_diabetes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abernathyclinic.mediscreen.service_diabetes.model.PatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.service.NotesReader;

class NotesReaderTest {

	@Autowired
	private NotesReader notesReader = new NotesReader();

	@DisplayName("Read a patient's note and return the amount of terms present.")
	@Test
	void givenReadingThePatientNote_whenRead_ThenItReturnTheAmountOfTermsPresent() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "Hémoglobine A1C");
		int result = notesReader.read(patientNote);

		assertEquals(1, result);
	}

	@DisplayName("Read a patient's note and return the amount of the many terms present.")
	@Test
	void givenReadingThePatientNote_whenRead_ThenItReturnTheAmountOfTheManyTermsPresent() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Microalbumine taille poids fumeur AnOrMaL Anticorps");
		int result = notesReader.read(patientNote);

		assertEquals(6, result);
	}

	@DisplayName("Read a patient's note and the return of amount terms is 0.")
	@Test
	void givenReadingThePatientNote_whenRead_ThenItReturnTheAmountOfTheTermsPresentWichIsNone() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "");
		int result = notesReader.read(patientNote);

		assertEquals(0, result);
	}
}
