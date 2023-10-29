package com.qph.attendancerecord.application.domain.service;

import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.in.RegisterAttendeeUseCase;
import com.qph.attendancerecord.application.port.in.model.RegisterAttendeeCommand;
import com.qph.attendancerecord.common.service.IdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterAttendeeService implements RegisterAttendeeUseCase {
    private final IdGenerator idGenerator;

    @Override
    public Attendee registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {
        log.info("Start registration of a new attendee # registerAttendeeCommand={}", registerAttendeeCommand);
        // TODO validate business rules
        // update domain model
        String name = registerAttendeeCommand.name().getValue();
        boolean isTuesdayOn = registerAttendeeCommand.tuesdayOn().getValue();
        boolean isThursdayOn = registerAttendeeCommand.thursdayOn().getValue();
        String id = idGenerator.generateStringId();
        String avatar = "avatar";
        Attendee newAttendee = Attendee.register(id, name, avatar, isTuesdayOn, isThursdayOn);
        // TODO call adapter to update data
        log.info("End registration of a new attendee # newAttendee={}", newAttendee);
        return newAttendee;
    }
}
