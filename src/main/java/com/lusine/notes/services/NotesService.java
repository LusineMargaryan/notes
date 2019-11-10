package com.lusine.notes.services;

import com.lusine.notes.exceptions.NoteNotFoundException;
import com.lusine.notes.models.Note;
import com.lusine.notes.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.util.Optional;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    public Note getNoteById(int id) {
        return notesRepository.findById(id).orElseThrow(NoteNotFoundException::new);
    }

    public Note createNote(Note note) {
        return notesRepository.save(note);
    }

    public Note updateNote(int id, Note newNote) {
        Optional<Note> noteMaybe = notesRepository.findById(id);

        return noteMaybe.
                map(noteToBeUpdated -> updateNoteFieldsAndSave(noteToBeUpdated, newNote))
                .orElseThrow(NoteNotFoundException::new);
    }

    public void deleteNote(int id) {
        try {
            notesRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NoteNotFoundException();
        }
    }

    private Note updateNoteFieldsAndSave(Note noteToBeUpdated, Note newNote) {
        noteToBeUpdated.setTitle(newNote.getTitle());
        noteToBeUpdated.setNote(newNote.getNote());
        noteToBeUpdated.setUserId(newNote.getUserId());
        noteToBeUpdated.setUpdatedAt(Instant.now().toEpochMilli());

        return notesRepository.save(noteToBeUpdated);
    }
}
