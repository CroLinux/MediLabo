package com.MediLabo.MSRisk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DiabetesRisk {

	private int patientId;

	private String riskLevel;

}
