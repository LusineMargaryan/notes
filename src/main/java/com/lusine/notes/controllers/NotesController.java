package com.lusine.notes.controllers;

import com.lusine.notes.models.Note;
import com.lusine.notes.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Note getNoteById(@PathVariable int id) {
        return notesService.getNoteById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Note createNote(@RequestBody Note note) {
        return notesService.createNote(note);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Note updateNote(@PathVariable int id, @RequestBody Note newNote) {
        return notesService.updateNote(id, newNote);
    }
}
