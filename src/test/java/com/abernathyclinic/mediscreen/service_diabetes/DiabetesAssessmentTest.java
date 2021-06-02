package com.abernathyclinic.mediscreen.service_diabetes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abernathyclinic.mediscreen.service_diabetes.model.DiabetesAssessmentResult;
import com.abernathyclinic.mediscreen.service_diabetes.model.Patient;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientAndPatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientDiabetesReport;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.service.DiabetesAssessment;

class DiabetesAssessmentTest {

	@Autowired
	private DiabetesAssessment diabetesAssessment = new DiabetesAssessment();

	private Patient patientMale = new Patient("lastName", "firstName", "01/01/2000", "male", "homeAddress",
			"phoneNumber");
	private Patient patientFemale = new Patient("lastName", "firstName", "01/01/2000", "female", "homeAddress",
			"phoneNumber");

	@DisplayName("Calculate the diabetes predisposition for a Male with less than 30yo which result in: NONE")
	@Test
	void givenGettingADiabetesReportForAMaleWithLessThan30Years_whenGetDiabetesAssessment_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfNone() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "");
		PatientAndPatientNote patientAndPatientNote = new PatientAndPatientNote(patientMale, patientNote);

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.getDiabetesAssessment(patientAndPatientNote);

		assertEquals(DiabetesAssessmentResult.NONE, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Male with less than 30yo which result in: IN_DANGER")
	@Test
	void givenGettingADiabetesReportForAMaleWithLessThan30Years_whenCalculateDiabetesReportForMale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfIn_Danger() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "Hémoglobine A1C, Microalbumine, Taille");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForMale(patientMale, patientNote);

		assertEquals(DiabetesAssessmentResult.IN_DANGER, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Male with less than 30yo which result in: EARLY_ONSET")
	@Test
	void givenGettingADiabetesReportForAMaleWithLessThan30Years_whenCalculateDiabetesReportForMale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfEarly_Onset() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForMale(patientMale, patientNote);

		assertEquals(DiabetesAssessmentResult.EARLY_ONSET, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Male with or more than 30yo which result in: NONE")
	@Test
	void givenGettingADiabetesReportForAMaleWithOrMoreThan30Years_whenCalculateDiabetesReportForMale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfNone() {
		patientMale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForMale(patientMale, patientNote);

		assertEquals(DiabetesAssessmentResult.NONE, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Male with or more than 30yo which result in: BORDERLINE")
	@Test
	void givenGettingADiabetesReportForAMaleWithOrMoreThan30Years_whenCalculateDiabetesReportForMale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfBorderline() {
		patientMale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "Hémoglobine A1C, Microalbumine, Taille");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForMale(patientMale, patientNote);

		assertEquals(DiabetesAssessmentResult.BORDERLINE, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Male with or more than 30yo which result in: IN_DANGER")
	@Test
	void givenGettingADiabetesReportForAMaleWithOrMoreThan30Years_whenCalculateDiabetesReportForMale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfIn_Danger() {
		patientMale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholestérol");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForMale(patientMale, patientNote);

		assertEquals(DiabetesAssessmentResult.IN_DANGER, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Male with or more than 30yo which result in: EARLY_ONSET")
	@Test
	void givenGettingADiabetesReportForAMaleWithOrMoreThan30Years_whenCalculateDiabetesReportForMale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfEarly_Onset() {
		patientMale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholestérol, Vertige");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForMale(patientMale, patientNote);

		assertEquals(DiabetesAssessmentResult.EARLY_ONSET, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with less than 30yo which result in: NONE")
	@Test
	void givenGettingADiabetesReportForAFemaleWithLessThan30Years_whenGetDiabetesAssessment_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfNone() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "");
		PatientAndPatientNote patientAndPatientNote = new PatientAndPatientNote(patientFemale, patientNote);

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.getDiabetesAssessment(patientAndPatientNote);

		assertEquals(DiabetesAssessmentResult.NONE, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with less than 30yo which result in: IN_DANGER")
	@Test
	void givenGettingADiabetesReportForAFemaleWithLessThan30Years_whenCalculateDiabetesReportForFemale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfIn_Danger() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "Hémoglobine A1C, Microalbumine, Taille, Poids");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForFemale(patientFemale, patientNote);

		assertEquals(DiabetesAssessmentResult.IN_DANGER, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with less than 30yo which result in: EARLY_ONSET")
	@Test
	void givenGettingADiabetesReportForAFemaleWithLessThan30Years_whenCalculateDiabetesReportForFemale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfEarly_Onset() {
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholestérol");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForFemale(patientFemale, patientNote);

		assertEquals(DiabetesAssessmentResult.EARLY_ONSET, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with or more than 30yo which result in: NONE")
	@Test
	void givenGettingADiabetesReportForAFemaleWithOrMoreThan30Years_whenCalculateDiabetesReportForFemale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfNone() {
		patientFemale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForFemale(patientFemale, patientNote);

		assertEquals(DiabetesAssessmentResult.NONE, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with or more than 30yo which result in: BORDERLINE")
	@Test
	void givenGettingADiabetesReportForAFemaleWithOrMoreThan30Years_whenCalculateDiabetesReportForFemale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfBorderline() {
		patientFemale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "Hémoglobine A1C, Microalbumine, Taille");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForFemale(patientFemale, patientNote);

		assertEquals(DiabetesAssessmentResult.BORDERLINE, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with or more than 30yo which result in: IN_DANGER")
	@Test
	void givenGettingADiabetesReportForAFemaleWithOrMoreThan30Years_whenCalculateDiabetesReportForFemale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfIn_Danger() {
		patientFemale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholestérol");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForFemale(patientFemale, patientNote);

		assertEquals(DiabetesAssessmentResult.IN_DANGER, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

	@DisplayName("Calculate the diabetes predisposition for a Female with or more than 30yo which result in: EARLY_ONSET")
	@Test
	void givenGettingADiabetesReportForAFemaleWithOrMoreThan30Years_whenCalculateDiabetesReportForFemale_ThenItReturnAnAccuratePatientDiabetesReportWhichResultOfEarly_Onset() {
		patientFemale.setDateOfBirth("01/01/0001");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(),
				"Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholestérol, Vertige");

		PatientDiabetesReport patientDiabetesReportResult = diabetesAssessment
				.calculateDiabetesReportForFemale(patientFemale, patientNote);

		assertEquals(DiabetesAssessmentResult.EARLY_ONSET, patientDiabetesReportResult.getDiabetesAssessmentResult());
	}

}
