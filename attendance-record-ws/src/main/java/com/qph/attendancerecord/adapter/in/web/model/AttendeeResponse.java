package com.qph.attendancerecord.adapter.in.web.model;

import com.qph.attendancerecord.application.domain.model.Attendee;

public record AttendeeResponse(String id, String name, String avatar, boolean tuesdayOn,
                               boolean thursdayOn) {
    public static AttendeeResponse fromAttendee(Attendee attendee) {
        return new AttendeeResponse(attendee.getId(),
            attendee.getName(),
            attendee.getAvatar(),
            attendee.isTuesdayOn(),
            attendee.isThursdayOn());
    }
}
