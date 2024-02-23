package com.qph.attendancerecord.application.port.out;

import com.qph.attendancerecord.application.domain.model.Attendee;

public interface RegisterAttendeeUpdateStatePort {
    Attendee registerAttendee(Attendee attendee);
}
