package com.qph.attendancerecord.application.port.in.model;

public abstract class StringValue {
    private final String value;

    protected StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
