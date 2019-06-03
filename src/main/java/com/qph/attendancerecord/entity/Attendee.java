package com.qph.attendancerecord.entity;

import java.util.Calendar;

public class Attendee {
    private Long id;

    private String name;

    private boolean presentOnTuesday;

    private boolean presentOnThursday;

    public Attendee(String name, boolean presentOnTuesday, boolean presentOnThursday) {
        this.id = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.presentOnTuesday = presentOnTuesday;
        this.presentOnThursday = presentOnThursday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPresentOnTuesday() {
        return presentOnTuesday;
    }

    public void setPresentOnTuesday(boolean presentOnTuesday) {
        this.presentOnTuesday = presentOnTuesday;
    }

    public boolean isPresentOnThursday() {
        return presentOnThursday;
    }

    public void setPresentOnThursday(boolean presentOnThursday) {
        this.presentOnThursday = presentOnThursday;
    }
}
