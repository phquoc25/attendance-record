package com.qph.attendancerecord.application.port.in.model;

import com.qph.attendancerecord.common.exception.ValidationException;

import static java.util.Objects.isNull;

public class Name extends StringValue {
    private Name(String value) {
        super(value);
    }

    public static Name fromString(String value) throws ValidationException {
        if (isNull(value) || value.isEmpty()) {
            throw ValidationException.withErrorMessage(String.format("name must not be empty or null but get %s", value));
        }
        return new Name(value);
    }
}
