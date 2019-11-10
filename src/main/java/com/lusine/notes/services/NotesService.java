package com.lusine.notes.services;

import com.lusine.notes.exceptions.NoteDoesNotBelongToUserException;
import com.lusine.notes.exceptions.NoteNotFoundException;
import com.lusine.notes.exceptions.UserNotFoundException;
import com.lusine.notes.models.Note;
import com.lusine.notes.models.User;
import com.lusine.notes.repositories.NotesRepository;
import com.lusine.notes.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Note getNoteById(int id, String currentUserEmail) {
        User user = usersRepository.findByEmail(currentUserEmail).orElseThrow(UserNotFoundException::new);
        Note note = notesRepository.findById(id).orElseThrow(NoteNotFoundException::new);

        if (note.getUserId() != user.getId()) {
            throw new NoteDoesNotBelongToUserException();
        }

        return note;
    }

    public Note createNote(Note note, String currentUserEmail) {
        User user = usersRepository.findByEmail(currentUserEmail).orElseThrow(UserNotFoundException::new);
        note.setUserId(user.getId());

        return notesRepository.save(note);
    }

    public Note updateNote(int id, Note newNote, String currentUserEmail) {
        Note noteToBeUpdated = notesRepository.findById(id).orElseThrow(NoteNotFoundException::new);
        User user = usersRepository.findByEmail(currentUserEmail).orElseThrow(UserNotFoundException::new);

        if (noteToBeUpdated.getUserId() != user.getId()) {
            throw new NoteDoesNotBelongToUserException();
        }

        noteToBeUpdated.setTitle(newNote.getTitle());
        noteToBeUpdated.setNote(newNote.getNote());
        noteToBeUpdated.setUserId(user.getId());
        noteToBeUpdated.setUpdatedAt(Instant.now().toEpochMilli());

        return notesRepository.save(noteToBeUpdated);
    }

    public void deleteNote(int id, String currentUserEmail) {
        Note noteToBeDeleted = notesRepository.findById(id).orElseThrow(NoteNotFoundException::new);
        User user = usersRepository.findByEmail(currentUserEmail).orElseThrow(UserNotFoundException::new);

        if (noteToBeDeleted.getUserId() != user.getId()) {
            throw new NoteDoesNotBelongToUserException();
        }

        try {
            notesRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NoteNotFoundException();
        }
    }
}
