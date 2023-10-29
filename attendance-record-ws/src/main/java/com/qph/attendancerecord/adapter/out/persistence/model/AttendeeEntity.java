package com.qph.attendancerecord.adapter.out.persistence.model;

import lombok.Data;

import java.util.UUID;

@Data
public class AttendeeEntity {
    private String id;

    private String name;

    private String avatar;

    private boolean tuesdayOn;

    private boolean thursdayOn;

    public AttendeeEntity() {
        // For jackson to deserialize
    }

    public AttendeeEntity(String name, boolean tuesdayOn, boolean thursdayOn) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tuesdayOn = tuesdayOn;
        this.thursdayOn = thursdayOn;
    }
}
