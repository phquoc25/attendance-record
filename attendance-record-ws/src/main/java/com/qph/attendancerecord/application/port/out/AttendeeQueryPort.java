package com.qph.attendancerecord.application.port.out;

import com.qph.attendancerecord.application.domain.model.Attendee;

public interface AttendeeQueryPort {
    Attendee getById(String id);
}
