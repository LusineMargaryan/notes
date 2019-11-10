package com.lusine.notes.controllers;

import com.lusine.notes.models.Note;
import com.lusine.notes.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Note getNoteById(@PathVariable int id, Principal principal) {
        return notesService.getNoteById(id, principal.getName());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Note createNote(@RequestBody Note note, Principal principal) {
        return notesService.createNote(note, principal.getName());
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Note updateNote(@PathVariable int id, @RequestBody Note newNote, Principal principal) {
        return notesService.updateNote(id, newNote, principal.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable int id, Principal principal) {
        notesService.deleteNote(id, principal.getName());
    }
}
