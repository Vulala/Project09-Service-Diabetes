package com.abernathyclinic.mediscreen.service_diabetes.service;

import org.springframework.stereotype.Service;

import com.abernathyclinic.mediscreen.service_diabetes.model.DiabetesAssessmentResult;
import com.abernathyclinic.mediscreen.service_diabetes.model.Patient;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientAndPatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientDiabetesReport;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientNote;

/**
 * Service used to assess the patient's diabetes predisposition. <br>
 */
@Service
public class DiabetesAssessment {

	private DiabetesAssessmentResult diabetesAssessmentResult;
	private AgeCalculator ageCalculator = new AgeCalculator();
	private NotesReader notesReader = new NotesReader();

	/**
	 * Check if the patient is a male or a female and then proceed to a diabetes
	 * assessment using the right method. <br>
	 * 
	 * @param patient
	 * @param patientNote
	 * @return {@link PatientDiabetesReport} : using the right method depending of
	 *         the patient's gender
	 */
	public PatientDiabetesReport getDiabetesAssessment(PatientAndPatientNote patientAndPatientNote) {
		Patient patient = patientAndPatientNote.getPatient();
		PatientNote patientNote = patientAndPatientNote.getPatientNote();
		if (patient.getGender().toUpperCase().contentEquals("M")
				|| (patient.getGender().toUpperCase().contentEquals("MALE"))) {
			return calculateDiabetesReportForMale(patient, patientNote);
		}
		return calculateDiabetesReportForFemale(patient, patientNote);
	}

	/**
	 * Calculate a diabetes predisposition report for male. <br>
	 * 
	 * @param patient
	 * @param patientNote
	 * @return {@link PatientDiabetesReport}
	 */
	public PatientDiabetesReport calculateDiabetesReportForMale(Patient patient, PatientNote patientNote) {
		int age = ageCalculator.getAgeFromBirthDate(patient.getDateOfBirth());
		int triggersTerms = notesReader.read(patientNote);

		if (triggersTerms <= 1 || age < 30 && triggersTerms <= 2) {
			diabetesAssessmentResult = DiabetesAssessmentResult.NONE;

		} else if (age >= 30 && triggersTerms >= 2 && triggersTerms <= 5) {
			diabetesAssessmentResult = DiabetesAssessmentResult.BORDERLINE;

		} else if (age < 30 && (triggersTerms == 3 || triggersTerms == 4)
				|| age >= 30 && (triggersTerms == 6 || triggersTerms == 7)) {
			diabetesAssessmentResult = DiabetesAssessmentResult.IN_DANGER;

		} else if (age < 30 && triggersTerms >= 5 || age >= 30 && triggersTerms >= 8) {
			diabetesAssessmentResult = DiabetesAssessmentResult.EARLY_ONSET;
		}

		return new PatientDiabetesReport(patient.getLastName(), patient.getFirstName(), age, diabetesAssessmentResult);
	}

	/**
	 * Calculate a diabetes predisposition report for female. <br>
	 * 
	 * @param patient
	 * @param patientNote
	 * @return {@link PatientDiabetesReport}
	 */
	public PatientDiabetesReport calculateDiabetesReportForFemale(Patient patient, PatientNote patientNote) {
		int age = ageCalculator.getAgeFromBirthDate(patient.getDateOfBirth());
		int triggersTerms = notesReader.read(patientNote);

		if (triggersTerms <= 1 || age < 30 && triggersTerms <= 3) {
			diabetesAssessmentResult = DiabetesAssessmentResult.NONE;

		} else if (age >= 30 && triggersTerms >= 2 && triggersTerms <= 5) {
			diabetesAssessmentResult = DiabetesAssessmentResult.BORDERLINE;

		} else if (age < 30 && (triggersTerms >= 4 && triggersTerms <= 6)
				|| age >= 30 && (triggersTerms == 6 || triggersTerms == 7)) {
			diabetesAssessmentResult = DiabetesAssessmentResult.IN_DANGER;

		} else if (age < 30 && triggersTerms >= 7 || age >= 30 && triggersTerms >= 8) {
			diabetesAssessmentResult = DiabetesAssessmentResult.EARLY_ONSET;
		}

		return new PatientDiabetesReport(patient.getLastName(), patient.getFirstName(), age, diabetesAssessmentResult);
	}
}
