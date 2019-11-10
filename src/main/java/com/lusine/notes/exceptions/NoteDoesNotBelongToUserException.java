package com.lusine.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Note does not belong to the user")
public class NoteDoesNotBelongToUserException extends RuntimeException {
}
