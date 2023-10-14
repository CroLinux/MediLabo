package com.MediLabo.MSPatient.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MediLabo.MSPatient.exceptions.PatientNotFoundException;
import com.MediLabo.MSPatient.exceptions.PatientsNotFoundException;
import com.MediLabo.MSPatient.model.Patient;
import com.MediLabo.MSPatient.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PatientController {

	@Autowired
	PatientService patientService;

	/**
	 * @RequestMapping("/getPatients") public Iterable<Patient> getPatients() {
	 * 
	 * Iterable<Patient> allPatients = patientService.getPatients();
	 * 
	 * if (!allPatients.iterator().hasNext()) { throw new
	 * PatientsNotFoundException("Aucun patient trouvé!"); } log.info("Request GET
	 * /getPatients called and get the result: " + allPatients); return allPatients;
	 * }
	 */

	@RequestMapping("/getPatients")
	public ResponseEntity<?> getPatients() {

		Iterable<Patient> allPatients = patientService.getPatients();

		if (!allPatients.iterator().hasNext()) {
			log.info("Request GET /getPatients called and nothinf found.");
			return ResponseEntity.badRequest().body("Aucun patient trouvé dans la base de données.");
		}
		log.info("Request GET /getPatients called and get the result: " + allPatients);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allPatients);
	}

	/**
	 * @RequestMapping("/getPatient") public Optional<Patient>
	 * getPatientById(@RequestParam int id) {
	 * 
	 * Optional<Patient> patientById = patientService.getPatientById(id); if
	 * (!patientById.isPresent()) // As we use an Optional table, we need to use
	 * isPresent() throw new PatientNotFoundException("Patient non trouvé!"); return
	 * patientById; }
	 */

	@RequestMapping("/getPatient")
	public ResponseEntity<?> getPatientById(@RequestParam int id) {
		// We verify if the patient already exist
		if (!patientService.existsById(id)) {
			// We return an error message if this doesn't exist
			log.info("Request GET /getPatient called for patient: " + id + " and patient not found");
			return ResponseEntity.badRequest().body("Ce patient n'existe pas dans la base de données.");

		} else {
			// Existing Patient? So we display this one
			log.info("Request GET /getPatient called for patient: " + id + " and patient found");
			Optional<Patient> patient = patientService.getPatientById(id);
			// return ResponseEntity.ok("Patient existe !").getBody();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(patient);
		}
	}

	/**
	 * @PostMapping("/addPatient") public Patient addPatientInDB(@RequestBody
	 * Patient patient) { log.info("Command POST /addPatients called for : {}",
	 * patient); patientService.addPatientInDB(patient); return patient;
	 * 
	 * }
	 */
	@PostMapping("/addPatient")
	public ResponseEntity<?> addPatient(@RequestBody Patient newPatient) {
		// We verify if the patient already exist
		if (patientService.existsByFirstNameAndLastNameAndDateOfBirth(newPatient.getFirstName(),
				newPatient.getLastName(), newPatient.getDateOfBirth())) {
			// We return an error message if this one already exist
			log.info("Command POST /addPatients called for : {} and patient already exists in database.",
					newPatient.toString());
			return ResponseEntity.badRequest().body("Ce patient existe déjà dans la base de données.");
		} else {
			// New Patient? So we add this one
			patientService.addPatientInDB(newPatient);
			log.info("Command POST /addPatients called for : {} and patient added in database.", newPatient.toString());
			return ResponseEntity.ok("Patient ajouté avec succès !");
		}
	}

	/**
	 * @PutMapping("/updatePatient") public Patient updatePatientInDB(@RequestBody
	 * Patient patient) { log.info("Command PUT /getPatients called for : {}",
	 * patient); patientService.updatePatientInDB(patient); return patient;
	 * 
	 * }
	 */
	@PutMapping("/updatePatient")
	public ResponseEntity<?> updatePatientInDB(@RequestBody Patient updatePatient) {
		Patient existingPatient = patientService.getPatientFirstNameAndLastNameAndDateOfBirth(
				updatePatient.getFirstName(), updatePatient.getLastName(), updatePatient.getDateOfBirth());
		// We verify if the patient already exist
		if (!patientService.existsByFirstNameAndLastNameAndDateOfBirth(updatePatient.getFirstName(),
				updatePatient.getLastName(), updatePatient.getDateOfBirth())) {
			// We return an error message if this doesn't exist
			log.info("Command PUT /updatePatientInDB called for : {} but the patient doesn't exists in database.",
					updatePatient.toString());
			return ResponseEntity.badRequest().body("Ce patient n'existe pas dans la base de données.");
		} else {
			// Existing Patient? So we update this one
			existingPatient.setFirstName(updatePatient.getFirstName());
			existingPatient.setLastName(updatePatient.getLastName());
			existingPatient.setDateOfBirth(updatePatient.getDateOfBirth());
			existingPatient.setGender(updatePatient.getGender());
			existingPatient.setAddress(updatePatient.getAddress());
			existingPatient.setPhoneNumber(updatePatient.getPhoneNumber());

			patientService.updatePatientInDB(existingPatient);
			log.info("Command PUT /updatePatientInDB called for : {} and patient updated.", updatePatient.toString());
			return ResponseEntity.ok("Patient mis à jour avec succès !");
		}
	}

	@DeleteMapping("/deletePatient")
	public ResponseEntity<?> deletePatient(@RequestParam int id) {
		// We verify if the patient already exist
		if (!patientService.existsById(id)) {
			// We return an error message if this doesn't exist
			log.info(
					"Request DELETE /deletePatient called for the Patient: " + id + " but doesn't exists in database.");
			return ResponseEntity.badRequest().body("Ce patient n'existe pas dans la base de données.");
		} else {
			// Existing Patient? So we delete this one
			patientService.deletePatientById(id);
			log.info("Request DELETE /deletePatient called for the Patient: " + id + " and deleted.");
			return ResponseEntity.ok("Patient effacé avec succès !");
		}
	}

}
