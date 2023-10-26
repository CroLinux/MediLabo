package com.MediLabo.MSRisk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.MediLabo.MSRisk.beans.NoteBean;
import com.MediLabo.MSRisk.beans.PatientBean;
import com.MediLabo.MSRisk.model.DiabetesRisk;
import com.MediLabo.MSRisk.proxies.MicroserviceRiskProxy;
import com.MediLabo.MSRisk.service.RiskService;

@Controller
public class RiskController {

	@Autowired
	private MicroserviceRiskProxy microserviceRiskProxy;

	@Autowired
	private RiskService riskService;

	@GetMapping("/ms-risk/risk/{id}")
	public ResponseEntity<?> getDiabetesRisk(@PathVariable int id) {

		// Initialization of the needed values.
		PatientBean patientInfo = microserviceRiskProxy.getPatientInfo(id);
		List<NoteBean> patientAllNotes = microserviceRiskProxy.getNotesByPatientId(id);

		// Check and Get the risk status for the requested patient.
		DiabetesRisk patientRisk = new DiabetesRisk();
		patientRisk = riskService.getRiskResultForPatient(patientInfo, patientAllNotes);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(patientRisk);

	}

}
