package com.qph.attendancerecord.entity;

import java.util.Calendar;

public class Attendee {
    private Long id;

    private String name;

    private String avatar;

    private boolean presentOnTuesday;

    private boolean presentOnThursday;

    public Attendee() {
        // For jackson to deserialize
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Attendee attendee = (Attendee) o;

        if (id != null ? !id.equals(attendee.id) : attendee.id != null) {
            return false;
        }
        return name != null ? name.equals(attendee.name) : attendee.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
