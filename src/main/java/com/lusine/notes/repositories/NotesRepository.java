package com.lusine.notes.repositories;

import com.lusine.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Note, Integer> {
    Note findById(int id);
}
