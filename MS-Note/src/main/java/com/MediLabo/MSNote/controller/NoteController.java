package com.MediLabo.MSNote.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MediLabo.MSNote.model.Note;
import com.MediLabo.MSNote.service.NoteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;

	/**
	 * Get all the existing notes.
	 * 
	 * @return
	 */
	@GetMapping("/ms-note/notes")
	public Iterable<Note> getAllNotes() {
		// return noteService.findAllNotes();
		Iterable<Note> allNotes = noteService.findAllNotes();
		log.info("Request GET /getAllNotes called and get the result: " + allNotes);
		return allNotes;
	}

	/**
	 * Get all the notes for a specific patient.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/ms-note/notes/{id}")
	public List<Note> getNotesByPatId(@PathVariable int id) {
		List<Note> allNotesByPatId = noteService.getNotesByPatId(id);
		return allNotesByPatId;
	}

	/**
	 * Get a specific note.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/ms-note/note/{id}")
	public Optional<Note> getNoteById(@PathVariable String id) {
		Optional<Note> noteById = noteService.getNoteById(id);
		return noteById;
	}

	/**
	 * Add/Create a note.
	 * 
	 * @param newNote
	 * @return
	 */
	@PostMapping("/ms-note/addnote")
	public Note addNoteInDB(@RequestBody Note newNote) {

		return noteService.addNoteInDB(newNote);
	}

	/**
	 * Update an existing note.
	 * 
	 * @param updatedNote
	 * @return
	 */
	@PutMapping("/ms-note/updatenote")
	public Note updateNoteInDB(@RequestBody Note updatedNote) {
		Optional<Note> existingNoteOptional = noteService.getNoteById(updatedNote.getId());
		if (!existingNoteOptional.isPresent()) {
			log.info("Request : " + updatedNote.toString());
		} else {
			Note existingNote = existingNoteOptional.get();
			existingNote.setPatId(updatedNote.getPatId());
			existingNote.setPatient(updatedNote.getPatient());
			existingNote.setNote(updatedNote.getNote());

			noteService.updateNoteInDB(existingNote);

		}
		return null;
	}

	/**
	 * Delete a sepcific note.
	 * 
	 * @param id
	 */
	@DeleteMapping("/ms-note/deletenote/{id}")
	public void deleteNoteFromDB(@PathVariable String id) {
		noteService.deleteNoteFromDB(id);
	}

}
