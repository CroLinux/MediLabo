package com.MediLabo.MSPatient.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediLabo.MSPatient.model.Patient;
import com.MediLabo.MSPatient.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Iterable<Patient> getAllPatients() {
		return patientRepository.findAll();

	}

	public Optional<Patient> getPatientById(int id) {
		return patientRepository.findById(id);
	}
	
	public Patient getPatientFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName,
			LocalDate dateOfBirth) {
				return patientRepository.getByFirstNameAndLastNameAndDateOfBirth(firstName, lastName, dateOfBirth);		
	}
	
	public void deletePatientById(int id) {
		patientRepository.deleteById(id);
	}

	public Patient addPatientInDB(Patient patient) {
		Patient patientToAdd = patientRepository.save(patient);
		return patientToAdd;
		
	}

	public Patient updatePatientInDB(Patient existingPatient) {
		Patient patientToUpdate = patientRepository.save(existingPatient);
		return patientToUpdate;
		
	}

	public boolean existsByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName,
			LocalDate dateOfBirth) {
		return patientRepository.existsByFirstNameAndLastNameAndDateOfBirth(firstName, lastName, dateOfBirth);
	}
	
	public boolean existsById(Integer Id) {
		return patientRepository.existsById(Id);
	}

}

