package com.lusine.notes.services;

import com.lusine.notes.models.Note;
import com.lusine.notes.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    public Optional<Note> getNoteById(int id) {
        return notesRepository.findById(id);
    }

    public Note createNote(Note note) {
        return notesRepository.save(note);
    }
}
