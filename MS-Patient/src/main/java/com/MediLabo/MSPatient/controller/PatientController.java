package com.MediLabo.MSPatient.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MediLabo.MSPatient.model.Patient;
import com.MediLabo.MSPatient.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PatientController {

	@Autowired
	PatientService patientService;

	/**
	 * Get all the existing patients from the database.
	 * 
	 * @return
	 */
	@RequestMapping("/ms-patient/getAllPatients")
	public ResponseEntity<?> getAllPatients(){

		Iterable<Patient> allPatients = patientService.getAllPatients();
		// We verify if object collection is empty.
		if (!allPatients.iterator().hasNext()) {
			log.info("Request GET /getPatients called and nothing found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allPatients);
			
		}
		// If not empty, we send the values.
		log.info("Request GET /getPatients called and get the result: " + allPatients);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allPatients);
	}

	/**
	 * Get all the informations for a specific patient.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/ms-patient/getPatient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable int id) {
		// We verify if the patient already exist
		if (!patientService.existsById(id)) {
			// We return an error message if this doesn't exist
			log.info("Request GET /getPatient called for patient: " + id + " and patient not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		} else {
			// Existing Patient? So we display this one
			log.info("Request GET /getPatient called for patient: " + id + " and patient found");
			Optional<Patient> patient = patientService.getPatientById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(patient);
		}
	}

	/**
	 * Add/Create a new patient.
	 * 
	 * @param newPatient
	 * @return
	 */
	@PostMapping("/ms-patient/addPatient")
	public ResponseEntity<?> addPatient(@RequestBody Patient newPatient) {
		// We verify if the patient already exist
		if (patientService.existsByFirstNameAndLastNameAndDateOfBirth(newPatient.getFirstName(),
				newPatient.getLastName(), newPatient.getDateOfBirth())) {
			// We return an error message if this one already exist
			log.info("Command POST /addPatients called for : {} and patient already exists in database.",
					newPatient.toString());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce patient existe déjà dans la base de données.");
		} else {
			// New Patient? So we add this one
			patientService.addPatientInDB(newPatient);
			log.info("Command POST /addPatients called for : {} and patient added in database.", newPatient.toString());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Patient ajouté avec succès !");
		}
	}

	/**
	 * Update an existing patient.
	 * 
	 * @param updatePatient
	 * @return
	 */
	@PutMapping("/ms-patient/updatePatient")
	public ResponseEntity<?> updatePatientInDB(@RequestBody Patient updatePatient) {
		Optional<Patient> existingPatientOptional = patientService.getPatientById(updatePatient.getIdPatient());
		// We verify if the patient already exist
		if (!existingPatientOptional.isPresent()) {
			// We return an error message if this doesn't exist
			log.info("Command PUT /updatePatientInDB called for : {} but the patient doesn't exists in database.",
					updatePatient.toString());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce patient n'existe pas dans la base de données.");
		} else {
			// Existing Patient? So we update this one
			Patient existingPatient = existingPatientOptional.get();
			existingPatient.setFirstName(updatePatient.getFirstName());
			existingPatient.setLastName(updatePatient.getLastName());
			existingPatient.setDateOfBirth(updatePatient.getDateOfBirth());
			existingPatient.setGender(updatePatient.getGender());
			existingPatient.setAddress(updatePatient.getAddress());
			existingPatient.setPhoneNumber(updatePatient.getPhoneNumber());

			patientService.updatePatientInDB(existingPatient);
			log.info("Command PUT /updatePatientInDB called for : {} and patient updated.", updatePatient.toString());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Patient mis à jour avec succès !");
		}
	}

	/**
	 * Delete an existing patient.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/ms-patient/deletePatient/{id}")
	// public ResponseEntity<?> deletePatient(@RequestParam int id) {
	public ResponseEntity<?> deletePatient(@PathVariable int id) {
		// We verify if the patient already exist
		if (!patientService.existsById(id)) {
			// We return an error message if this doesn't exist
			log.info(
					"Request DELETE /deletePatient called for the Patient: " + id + " but doesn't exists in database.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce patient n'existe pas dans la base de données.");
		} else {
			// Existing Patient? So we delete this one
			patientService.deletePatientById(id);
			log.info("Request DELETE /deletePatient called for the Patient: " + id + " and deleted.");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Patient effacé avec succès !");
		}
	}

}
