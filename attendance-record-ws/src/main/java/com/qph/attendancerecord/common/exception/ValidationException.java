package com.qph.attendancerecord.common.exception;

public class ValidationException extends RuntimeException {
    private ValidationException(String errorMessage) {
        super(errorMessage);
    }

    public static ValidationException withErrorMessage(String errorMessage) {
        return new ValidationException(errorMessage);
    }
}
