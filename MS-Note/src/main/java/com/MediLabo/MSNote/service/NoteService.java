package com.MediLabo.MSNote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediLabo.MSNote.model.Note;
import com.MediLabo.MSNote.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public List<Note> findAllNotes() {
		return noteRepository.findAll();		
	}
	
	public List<Note> getNotesByPatId(int patid){
		return noteRepository.findNotesByPatId(patid);	
	}
	
	public Note addNoteInDB(Note newNote) {
		Note noteToAdd = noteRepository.save(newNote);
		return noteToAdd;
	}
	
	public Note updateNoteInDB(Note updatedNote) {
		return noteRepository.save(updatedNote);
	}
	
	public void deleteNoteFromDB(String id) {
		noteRepository.deleteById(id);
	}

	public Optional<Note> getNoteById(String id) {		
		return noteRepository.findById(id);
	}

	
}
