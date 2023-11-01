package com.MediLabo.MSPatient.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MediLabo.MSPatient.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository <Patient, Integer>{
	
	// Customized method to check if the Patient already exist in the Database
    boolean existsByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, LocalDate dateOfBirth);
    
    // Get by specific entries
    Patient getByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, LocalDate dateOfBirth);


}

