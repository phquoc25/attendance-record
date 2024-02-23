package com.qph.attendancerecord.application.port.in;

import com.qph.attendancerecord.application.domain.model.Attendee;

import java.util.List;

public interface AttendeeQueryUseCase {

    Attendee getAttendeeById(String id);

    List<Attendee> getAllAttendees();
}
