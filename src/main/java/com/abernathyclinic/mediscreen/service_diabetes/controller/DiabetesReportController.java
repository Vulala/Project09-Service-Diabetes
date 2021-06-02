package com.abernathyclinic.mediscreen.service_diabetes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abernathyclinic.mediscreen.service_diabetes.model.PatientAndPatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientDiabetesReport;
import com.abernathyclinic.mediscreen.service_diabetes.service.DiabetesAssessment;

/**
 * Main controller of this micro-service, it allow the user to generate a
 * diabetes report for a patient. <br>
 */
@RestController
public class DiabetesReportController {

	@Autowired
	private DiabetesAssessment diabetesAssessment;

	@GetMapping("/")
	public String index() {
		return "Welcome on Service-Diabetes ! \n Micro-service targeted to generate diabetes predisposition report for the patient.";
	}

	@GetMapping("/assess")
	public PatientDiabetesReport getDiabetesAssessment(@RequestBody PatientAndPatientNote patientAndPatientNote) {
		return diabetesAssessment.getDiabetesAssessment(patientAndPatientNote);
	}

}
