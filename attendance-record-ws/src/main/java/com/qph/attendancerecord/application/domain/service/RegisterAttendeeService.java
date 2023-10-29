package com.qph.attendancerecord.application.domain.service;

import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.in.RegisterAttendeeUseCase;
import com.qph.attendancerecord.application.port.in.model.RegisterAttendeeCommand;
import com.qph.attendancerecord.common.service.IdGenerator;

public class RegisterAttendeeService implements RegisterAttendeeUseCase {
    private IdGenerator idGenerator;

    @Override
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        // TODO validate business rules
        // update domain model
        String name = registerAttendeeCommand.name().getValue();
        boolean isTuesdayOn = registerAttendeeCommand.tuesdayOn().getValue();
        boolean isThursdayOn = registerAttendeeCommand.thursdayOn().getValue();
        String id = idGenerator.generateStringId();
        String avatar = "avatar";
        Attendee newAttendee = Attendee.register(id, name, avatar, isTuesdayOn, isThursdayOn);
        // TODO call adapter to update data
    }
}
