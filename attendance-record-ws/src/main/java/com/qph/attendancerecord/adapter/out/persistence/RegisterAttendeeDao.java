package com.qph.attendancerecord.adapter.out.persistence;

import com.qph.attendancerecord.adapter.out.persistence.model.AttendeeEntity;
import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.out.RegisterAttendeeUpdateStatePort;
import com.qph.attendancerecord.common.exception.DataAccessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterAttendeeDao implements RegisterAttendeeUpdateStatePort {
    private final AttendeeRepository attendeeRepository;

    @Override
    public Attendee registerAttendee(Attendee attendee) {
        try {
            final AttendeeEntity attendeeEntity = this.attendeeToEntity(attendee);
            AttendeeEntity registeredAttendee = this.attendeeRepository.save(attendeeEntity);
            return this.entityToAttendee(registeredAttendee);
        } catch (IOException exception) {
            log.error("Failed to access DB when registering new attendee",
                exception);
            throw DataAccessException.withErrorMessage("Failed to access file " + exception.getMessage());
        }
    }

    private Attendee entityToAttendee(AttendeeEntity attendeeEntity) {
        return Attendee.register(attendeeEntity.getId(),
            attendeeEntity.getName(),
            attendeeEntity.getAvatar(),
            attendeeEntity.isTuesdayOn(),
            attendeeEntity.isThursdayOn());
    }

    private AttendeeEntity attendeeToEntity(Attendee attendee) {
        return new AttendeeEntity(attendee.getId(),
            attendee.getName(),
            attendee.getAvatar(),
            attendee.isTuesdayOn(),
            attendee.isThursdayOn());
    }
}
