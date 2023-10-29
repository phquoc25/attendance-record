package com.qph.attendancerecord.application.port.in;

import com.qph.attendancerecord.application.port.in.model.RegisterAttendeeCommand;

public interface RegisterAttendeeUseCase {
    void registerAttendee(final RegisterAttendeeCommand registerAttendeeCommand);
}
