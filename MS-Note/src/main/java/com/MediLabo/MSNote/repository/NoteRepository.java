package com.MediLabo.MSNote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MediLabo.MSNote.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String>{

	List<Note> findNotesByPatId(int patid);

}
