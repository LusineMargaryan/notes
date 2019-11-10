package com.lusine.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Note is not found")
public class NoteNotFoundException extends RuntimeException {
}
