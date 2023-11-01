package com.MediLabo.MSRisk.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.MediLabo.MSRisk.beans.NoteBean;
import com.MediLabo.MSRisk.beans.PatientBean;

@FeignClient(name = "MsGatewayApplication", url = "localhost:8080")
//@FeignClient(name = "MsGatewayApplication", url = "medilabo-msgatewayapp:8080")
public interface MicroserviceRiskProxy {

	@GetMapping(value = "/ms-patient/getPatient/{id}")
	PatientBean getPatientInfo(@PathVariable("id") int id);

	@GetMapping(value = "/ms-note/notes/{id}")
	List<NoteBean> getNotesByPatientId(@PathVariable("id") int id);

}
