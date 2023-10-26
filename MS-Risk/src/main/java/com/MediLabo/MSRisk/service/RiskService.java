package com.MediLabo.MSRisk.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import com.MediLabo.MSRisk.beans.NoteBean;
import com.MediLabo.MSRisk.beans.PatientBean;
import com.MediLabo.MSRisk.model.DiabetesRisk;

@Service
public class RiskService {

	public DiabetesRisk getRiskResultForPatient(PatientBean patientInfo, List<NoteBean> patientAllNotes) {

		DiabetesRisk diabetesRiskPatient = new DiabetesRisk();

		// Key words to search for.
		List<String> listKeyWords = List.of("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Fumeuse",
				"Anormal", "Cholestérol", "Vertiges", "Rechute", "Réaction", "Anticorps");

		// Get the age of the patient.
		int patientAge = calculateAge(patientInfo.getDateOfBirth());

		// Search for the words, so the trigger's value.
		int countManyTimesKeyWordsInNotes = 0;

		for (NoteBean note : patientAllNotes) {
			for (String keyword : listKeyWords) {
				String noteLowerCase = note.getNote().toLowerCase();
				String keywordLowerCase = keyword.toLowerCase();

				if (noteLowerCase.contains(keywordLowerCase)) {
					countManyTimesKeyWordsInNotes++;
				}
			}
		}

		// Set and Get the final values for the return result.
		diabetesRiskPatient.setPatientId(patientInfo.getIdPatient());
		diabetesRiskPatient
				.setRiskLevel(calculateRiskLevel(patientAge, patientInfo.getGender(), countManyTimesKeyWordsInNotes));

		return diabetesRiskPatient;
	}

	/**
	 * Method to get the risk status of the patient regarding the provided rules.
	 * 
	 * @param age
	 * @param gender
	 * @param triggerCount
	 * @return (risk status)
	 */
	public String calculateRiskLevel(int age, String gender, int triggerCount) {
		if (triggerCount == 0) {
			return "None";
		} else if (triggerCount >= 2 && triggerCount <= 5 && age > 30) {
			return "Borderline";
		} else if (age < 30) {
			if (gender.equals("M")) {
				if (triggerCount >= 3 && triggerCount <= 4) {
					return "InDanger";
				} else if (triggerCount >= 5) {
					return "EarlyOnset";
				}
			} else if (gender.equals("F")) {
				if (triggerCount >= 4 && triggerCount <= 6) {
					return "InDanger";
				} else if (triggerCount >= 7) {
					return "EarlyOnset";
				}
			}
		} else {
			if (triggerCount >= 6 && triggerCount <= 7 && age > 30) {
				return "InDanger";
			} else if (triggerCount >= 8) {
				return "EarlyOnset";
			}
		}
		return "None"; // If not in those rules, we send back this value.
	}

	/**
	 * Method to calculate the age of the patient.
	 * 
	 * @param patientDateOfBirth
	 * @return (age)
	 */
	public int calculateAge(LocalDate patientDateOfBirth) {
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(patientDateOfBirth, currentDate);
		return period.getYears();
	}
}
