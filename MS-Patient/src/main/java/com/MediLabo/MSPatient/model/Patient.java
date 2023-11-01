package com.MediLabo.MSPatient.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "patient")
@DynamicUpdate
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_patient")
	private Integer idPatient;

	@Column(name = "firstname")
	@NotBlank(message = "Firstname is mandatory")
	private String firstName;

	@Column(name = "lastname")
	@NotBlank(message = "Lastname is mandatory")
	private String lastName;

	@Column(name = "date_of_birth")
	@NotBlank(message = "Date of birth is mandatory with format YYYY-MM-DD")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@Column(name = "gender")
	@NotBlank(message = "Gender is mandatory")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "phonenumber")
	private String phoneNumber;
	
	

}

