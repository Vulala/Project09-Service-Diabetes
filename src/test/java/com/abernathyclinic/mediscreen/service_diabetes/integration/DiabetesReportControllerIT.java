package com.abernathyclinic.mediscreen.service_diabetes.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.abernathyclinic.mediscreen.service_diabetes.model.Patient;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientAndPatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.model.PatientNote;
import com.abernathyclinic.mediscreen.service_diabetes.service.DiabetesAssessment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
class DiabetesReportControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DiabetesAssessment diabetesAssessment;

	@DisplayName("Injected Components Are Rightly Setup")
	@Test
	void injectedComponentsAreRightlySetup() {
		assertThat(mockMvc).isNotNull();
		assertThat(diabetesAssessment).isNotNull();
	}

	@DisplayName("GET : /")
	@Test
	void givenGettingTheIndex_whenIndex_thenItReturnTheIndex() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		assertEquals(200, status);
	}

	@DisplayName("GET : /assess")
	@Test
	void givenGettingADiabetesReportForAPatient_whenGetDiabetesAssessment_thenItReturnAnAccuratePatientDiabetesReport()
			throws Exception {
		Patient patientMale = new Patient("lastName", "firstName", "2000-01-01", "male", "homeAddress", "phoneNumber");
		PatientNote patientNote = new PatientNote(UUID.randomUUID(), "Microalbumine");
		PatientAndPatientNote patientAndPatientNote = new PatientAndPatientNote(patientMale, patientNote);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String requestBody = objectWriter.writeValueAsString(patientAndPatientNote);

		MvcResult mvcResult = mockMvc
				.perform(post("/assess").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andDo(print())
				.andExpect(jsonPath("$.diabetesAssessmentResult").value("NONE"))
				.andExpect(jsonPath("$.age").value("21")).andReturn();
		int status = mvcResult.getResponse().getStatus();

		assertEquals(200, status);
	}

}
