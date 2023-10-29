package com.qph.attendancerecord.application.domain.service;

import com.qph.attendancerecord.application.port.in.RegisterAttendeeUseCase;
import com.qph.attendancerecord.application.port.in.model.RegisterAttendeeCommand;

public class RegisterAttendeeService implements RegisterAttendeeUseCase {
    @Override
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        // validate business rules
        // update domain model
        // call adapter to update data
    }
}
