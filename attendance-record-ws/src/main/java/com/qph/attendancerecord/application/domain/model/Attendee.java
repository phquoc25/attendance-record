package com.qph.attendancerecord.application.domain.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = {"id"})
public class Attendee {
    private final String id;

    private String name;

    private String avatar;

    private boolean tuesdayOn;

    private boolean thursdayOn;

    private Attendee(String id, String name, String avatar, boolean tuesdayOn, boolean thursdayOn) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.tuesdayOn = tuesdayOn;
        this.thursdayOn = thursdayOn;
    }

    public static Attendee register(String id, String name, String avatar, boolean tuesdayOn, boolean thursdayOn) {
        return new Attendee(id, name, avatar, tuesdayOn, thursdayOn);
    }
}
