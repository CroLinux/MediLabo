package com.MediLabo.MSFrontend.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DiabetesRiskBean {
	
	private int patientId;

	private String riskLevel;

}
