package com.qph.attendancerecord.adapter.in.web.exception;

public record ApplicationServerError(int code, String errorMessage) {
    public static final int VALIDATION_FAILURE = 2000;
    public static final int DATA_ACCESS_FAILURE = 2010;

    public static ApplicationServerError validationError(String errorMessage) {
        return new ApplicationServerError(VALIDATION_FAILURE,
            errorMessage);
    }

    public static ApplicationServerError dataAccessError(String errorMessage) {
        return new ApplicationServerError(DATA_ACCESS_FAILURE,
            errorMessage);
    }
}
