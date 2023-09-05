package com.qph.attendancerecord.services;

import com.qph.attendancerecord.entity.Attendee;

import java.io.IOException;
import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees();

    void updateAttendees(List<Attendee> attendees) throws IOException;

    Attendee save(Attendee attendee) throws IOException;

    String remove(String id) throws IOException;
}
