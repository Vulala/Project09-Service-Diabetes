package com.abernathyclinic.mediscreen.service_diabetes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abernathyclinic.mediscreen.service_diabetes.model.PatientAndPatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientDiabetesReport;
import com.abernathyclinic.mediscreen.service_diabetes.service.DiabetesAssessment;

/**
 * Main controller of this micro-service, it allow the user to generate a
 * diabetes predisposition report for a patient. <br>
 */
@RestController
public class DiabetesReportController {

	@Autowired
	private DiabetesAssessment diabetesAssessment;

	@GetMapping("/")
	public String index() {
		return "Welcome on Service-Diabetes ! \n Micro-service targeted to generate diabetes predisposition report for the patient.";
	}

	/**
	 * POST mapping to generate a diabetes assessment for a patient. <br>
	 * It need the patient's demographic information and the patient's practitioners
	 * notes unified in a single POJO {@link PatientAndPatientNote}. <br>
	 * <br>
	 * Note : this method could be a GET mapping with a body, howewer, this is not
	 * what it is intended to be done by a lot of API such as OpenFeign, who cannot
	 * make a GET request with a body. <br>
	 * 
	 * @param patientAndPatientNote
	 * @return {@link PatientDiabetesReport}
	 */
	@PostMapping("/assess")
	public PatientDiabetesReport getDiabetesAssessment(@RequestBody PatientAndPatientNote patientAndPatientNote) {
		return diabetesAssessment.getDiabetesAssessment(patientAndPatientNote);
	}

}
