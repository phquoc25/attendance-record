package com.qph.attendancerecord.adapter.in.web.exception;

import com.qph.attendancerecord.common.exception.AttendeeNotFoundException;
import com.qph.attendancerecord.common.exception.DataAccessException;
import com.qph.attendancerecord.common.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ApplicationServerError> validationExceptionHandler(ValidationException ex) {
        return ResponseEntity.badRequest().body(ApplicationServerError.validationError(ex.getMessage()));
    }

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<ApplicationServerError> dataAccessExceptionHandler(DataAccessException ex) {
        return ResponseEntity.badRequest().body(ApplicationServerError.dataAccessError(ex.getMessage()));
    }

    @ExceptionHandler(value = AttendeeNotFoundException.class)
    public ResponseEntity<ApplicationServerError> attendeeNotFoundExceptionHandler(AttendeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApplicationServerError.dataAccessError(ex.getMessage()));
    }
}
