package com.qph.attendancerecord.application.port.out;

import com.qph.attendancerecord.application.domain.model.Attendee;

import java.util.List;

public interface AttendeeQueryPort {
    Attendee getById(String id);

    List<Attendee> getAll();
}
