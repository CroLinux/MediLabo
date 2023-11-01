package com.MediLabo.MSFrontend.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@ToString
public class PatientBean {

	private Integer idPatient;

	@NotBlank(message = "Firstname is mandatory")
	private String firstName;

	@NotBlank(message = "Lastname is mandatory")
	private String lastName;

	@NotBlank(message = "Date of birth is mandatory")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Gender is mandatory")
	private String gender;

	private String address;

	@Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must contain ten digits")
	private String phoneNumber;
	
}
