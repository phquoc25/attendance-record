package com.qph.attendancerecord.application.port.in;

import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.in.model.RegisterAttendeeCommand;

public interface RegisterAttendeeUseCase {
    Attendee registerAttendee(final RegisterAttendeeCommand registerAttendeeCommand);
}
