package com.qph.attendancerecord.application.port.in.model;

import com.qph.attendancerecord.common.exception.ValidationException;

public class ThursdayOn extends BooleanValue {
    private ThursdayOn(boolean value) {
        super(value);
    }

    public static ThursdayOn fromString(String value) throws ValidationException {
        if (TRUE.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value)) {
            return new ThursdayOn(Boolean.parseBoolean(value));
        }
        throw ValidationException.withErrorMessage(String.format("ThursdayOn must be true or false but get %s", value));
    }
}
