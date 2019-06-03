package com.qph.attendancerecord.services;

import com.qph.attendancerecord.entity.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees();

    void updateAttendee(Attendee attendee);
}
