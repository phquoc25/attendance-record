package com.qph.attendancerecord.application.port.in.model;

public abstract class BooleanValue {
    protected static final String TRUE = "true";
    protected static final String FALSE = "false";
    private final boolean value;

    protected BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
