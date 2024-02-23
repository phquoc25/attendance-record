package com.qph.attendancerecord.adapter.out.persistence.model;

import lombok.Data;

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

    public AttendeeEntity(String id, String name, String avatar, boolean tuesdayOn, boolean thursdayOn) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.tuesdayOn = tuesdayOn;
        this.thursdayOn = thursdayOn;
    }
}
