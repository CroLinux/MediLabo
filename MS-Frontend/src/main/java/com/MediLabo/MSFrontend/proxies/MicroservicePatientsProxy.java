package com.MediLabo.MSFrontend.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.MediLabo.MSFrontend.beans.PatientBean;

@FeignClient(name = "MsGatewayApplication", url = "localhost:8080")
public interface MicroservicePatientsProxy {

	@GetMapping(value = "/ms-patient/getAllPatients")
	List<PatientBean> patientsList();

	@GetMapping(value = "/ms-patient/getPatient/{id}")
	PatientBean getPatientInfo(@PathVariable("id") int id);
	
	@PostMapping("/ms-patient/addPatient")
	ResponseEntity<String> processAddNewPatient(@RequestBody PatientBean newPatient);

	@PutMapping("/ms-patient/updatePatient")
	ResponseEntity<String> processUpdatePatient(@RequestBody PatientBean updatePatient);
	
	@DeleteMapping("/ms-patient/deletePatient/{id}")
	void processDeletePatient(@PathVariable("id") int id);

}
