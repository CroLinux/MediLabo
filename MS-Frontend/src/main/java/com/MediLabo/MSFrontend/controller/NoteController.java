package com.MediLabo.MSFrontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MediLabo.MSFrontend.beans.DiabetesRiskBean;
import com.MediLabo.MSFrontend.beans.NoteBean;
import com.MediLabo.MSFrontend.beans.PatientBean;
import com.MediLabo.MSFrontend.proxies.MicroservicePatientsProxy;

@Controller
public class NoteController {

	@Autowired
	private MicroservicePatientsProxy patientsProxy;

	/**
	 * Get all the notes for a specific patient with his ID.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/ms-frontend/patient/notes/{id}")
	public String getNotesByPatId(@PathVariable int id, Model model) {
		PatientBean patientInfo = patientsProxy.getPatientInfo(id);
		model.addAttribute("patient", patientInfo);
		List<NoteBean> patientNotes = patientsProxy.getNotesByPatientId(id);
		model.addAttribute("notes", patientNotes);
		DiabetesRiskBean patientRisk = patientsProxy.getPatientRisk(id);
		model.addAttribute("riskpatient", patientRisk);
		return "patientnotes.html";
	}

	/**
	 * Get the information from a specific note.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/ms-frontend/patient/note/info/{id}")
	public String getNoteInfo(@PathVariable String id, Model model) {
		Optional<NoteBean> optionalNote = patientsProxy.getNoteById(id);
		NoteBean noteInfo = optionalNote.orElse(null);
		model.addAttribute("note", noteInfo);
		return "noteinfo.html";
	}

	/**
	 * Add/Create a new note for a specific patient.
	 * 
	 * @param model
	 * @param patId
	 * @param patient
	 * @return
	 */
	@RequestMapping("/ms-frontend/addnewnote")
	public String addNewNote(Model model, @RequestParam("patId") int patId, @RequestParam("patient") String patient) {
		NoteBean newNote = new NoteBean();
		newNote.setPatId(patId);
		newNote.setPatient(patient);
		model.addAttribute("note", newNote);
		return "addnote.html";
	}

	/**
	 * Processing for a new note.
	 * 
	 * @param newNote
	 * @return
	 */
	@PostMapping("/ms-frontend/process_add_note")
	public String processAddNewNote(NoteBean newNote) {
		patientsProxy.processAddNewNote(newNote);
		return "redirect:/ms-frontend/patients";
	}

	/**
	 * Processing the update of a note.
	 * 
	 * @param updatedNote
	 * @return
	 */
	@PostMapping("/ms-frontend/process_update_note")
	public String processUpdateNote(NoteBean updatedNote) {
		patientsProxy.processUpdateNote(updatedNote);
		return "redirect:/ms-frontend/patients";
	}

	/**
	 * Delete a specific note.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/ms-frontend/process_delete_note/{id}")
	public String processDeleteNote(@PathVariable(value = "id") String id) {
		patientsProxy.processDeleteNote(id);
		return "redirect:/ms-frontend/patients";
	}

}
