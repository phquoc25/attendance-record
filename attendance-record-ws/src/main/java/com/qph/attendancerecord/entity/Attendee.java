package com.qph.attendancerecord.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Attendee {
    private String id;

    private String name;

    private String avatar;

    private boolean tuesdayOn;

    private boolean thursdayOn;

    public Attendee() {
        // For jackson to deserialize
    }

    public Attendee(String name, boolean tuesdayOn, boolean thursdayOn) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tuesdayOn = tuesdayOn;
        this.thursdayOn = thursdayOn;
    }
}
