package com.MediLabo.MSFrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MediLabo.MSFrontend.beans.PatientBean;
import com.MediLabo.MSFrontend.proxies.MicroservicePatientsProxy;

@Controller
public class PatientController {

	@Autowired
	private MicroservicePatientsProxy patientsProxy;

	public PatientController(MicroservicePatientsProxy patientsProxy) {
		this.patientsProxy = patientsProxy;
	}

	/**
	 * Get the list of the actual patients.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/ms-frontend/patients")
	public String patients(Model model) {
		List<PatientBean> patients = patientsProxy.patientsList();
		model.addAttribute("patients", patients);
		return "patients.html";
	}

	/**
	 * Get all the information for a specific patient.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/ms-frontend/patient/info/{id}")
	public String getpatientInfo(@PathVariable int id, Model model) {
		PatientBean patientInfo = patientsProxy.getPatientInfo(id);
		model.addAttribute("patient", patientInfo);
		return "patientinfo.html";
	}

	/**
	 * Add/Create a new patient.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/ms-frontend/addnewpatient")
	public String addNewPatient(Model model) {
		PatientBean newPatient = new PatientBean();
		model.addAttribute("patient", newPatient);
		return "addpatient.html";
	}

	/**
	 * Processing to add a new patient.
	 * 
	 * @param newPatient
	 * @return
	 */
	@PostMapping("/ms-frontend/process_add_patient")
	public String processAddNewPatient(PatientBean newPatient) {
		patientsProxy.processAddNewPatient(newPatient);
		return "redirect:/ms-frontend/patients";
	}

	/**
	 * Processing to update a patient.
	 * 
	 * @param updatedPatient
	 * @return
	 */
	@PostMapping("/ms-frontend/process_update_patient")
	public String processUpdatePatient(PatientBean updatedPatient) {
		patientsProxy.processUpdatePatient(updatedPatient);
		return "redirect:/ms-frontend/patients";
	}

	/**
	 * Delete a specific patient.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/ms-frontend/process_delete_patient/{id}")
	public String processDeletePatient(@PathVariable(value = "id") Integer id) {
		patientsProxy.processDeletePatient(id);
		return "redirect:/ms-frontend/patients";
	}

}
