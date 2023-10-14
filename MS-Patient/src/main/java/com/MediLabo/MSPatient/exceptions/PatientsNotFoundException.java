package com.MediLabo.MSPatient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // to send the correct error code 404
public class PatientsNotFoundException extends RuntimeException {
	
	public PatientsNotFoundException(String message) {
		super(message);
	}

}
