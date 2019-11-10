package com.lusine.notes.controllers;

import com.lusine.notes.models.Note;
import com.lusine.notes.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable int id) {
        return notesService.getNoteById(id);
    }
}
