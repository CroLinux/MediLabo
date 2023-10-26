package com.MediLabo.MSRisk.beans;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PatientBean {

	private Integer idPatient;

	private String firstName;

	private String lastName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	private String gender;

	private String address;

	private String phoneNumber;

}
