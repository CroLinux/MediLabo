package com.MediLabo.MSFrontend.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoteBean {
	
	private String id;

	private int patId;
	
	private String patient;
	
	private String note;

}
