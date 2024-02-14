package com.qph.attendancerecord.common.exception;

public class AttendeeNotFoundException extends RuntimeException {
    public AttendeeNotFoundException(String message) {
        super(message);
    }

    public static AttendeeNotFoundException notFoundException(String id) {
        return new AttendeeNotFoundException(String.format("Attendee not found: id=%s",
            id));
    }
}
