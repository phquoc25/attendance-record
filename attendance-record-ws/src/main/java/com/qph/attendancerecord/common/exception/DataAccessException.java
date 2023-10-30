package com.qph.attendancerecord.common.exception;

public class DataAccessException extends RuntimeException {
    private DataAccessException(String message) {
        super(message);
    }

    public static DataAccessException withErrorMessage(String message) {
        return new DataAccessException(message);
    }
}
