package com.qph.attendancerecord.application.port.in;

import com.qph.attendancerecord.application.domain.model.Attendee;

public interface AttendeeQueryUseCase {

    Attendee getAttendeeById(String id);
}
