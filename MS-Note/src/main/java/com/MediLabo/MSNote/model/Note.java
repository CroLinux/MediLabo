package com.MediLabo.MSNote.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Document(collection = "patient_notes")
public class Note {
	
	@Id
	private String id;

	@NotNull
	private int patId;
	
	private String patient;
	
	private String note;

}
