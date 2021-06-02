package com.abernathyclinic.mediscreen.service_diabetes.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abernathyclinic.mediscreen.service_diabetes.model.PatientNote;

/**
 * Service used to read the patient's note and retrieve the amount of triggers
 * terms that it contains. <br>
 */
@Service
public class NotesReader {

	private List<String> termsList = Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur",
			"Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");

	/**
	 * Read a patient's notes and count the amount of terms triggering the diabetes
	 * present in these notes. <br>
	 * 
	 * @param patientNote
	 * @return int : the amount of terms triggering the diabetes present in the
	 *         patient's notes
	 */
	public int read(PatientNote patientNote) {
		int triggersTerms = 0;
		String notes = patientNote.getNotes();

		for (int i = 0; i < termsList.size(); i++) {
			if (notes.toLowerCase().contains(termsList.get(i).toLowerCase())) {
				triggersTerms += 1;
			}
		}

		return triggersTerms;
	}

}
