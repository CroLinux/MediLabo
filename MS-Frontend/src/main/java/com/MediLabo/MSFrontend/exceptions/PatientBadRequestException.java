package com.MediLabo.MSFrontend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)

public class PatientBadRequestException extends RuntimeException {

	public PatientBadRequestException(String message) {

		super(message);

	}

}