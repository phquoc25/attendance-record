package com.qph.attendancerecord.adapter.in.web.exception;

public record ApplicationServerError(int code, String errorMessage) {
    public static final int VALIDATION_FAILURE = 2000;

    public static ApplicationServerError validationError(String errorMessage) {
        return new ApplicationServerError(VALIDATION_FAILURE, errorMessage);
    }
}
