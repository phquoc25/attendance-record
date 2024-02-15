package com.qph.attendancerecord.adapter.out.persistence;

import com.qph.attendancerecord.adapter.out.persistence.model.AttendeeEntity;
import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.out.AttendeeQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendeeQueryDao implements AttendeeQueryPort {
    private final AttendeeRepository repository;

    @Override
    public Attendee getById(String id) {
        return entityToAttendee(repository.getById(id));
    }

    @Override
    public List<Attendee> getAll() {
        return repository.getAllAttendees().stream().map(AttendeeQueryDao::entityToAttendee).toList();
    }

    private static Attendee entityToAttendee(AttendeeEntity attendeeEntity) {
        return Attendee.register(attendeeEntity.getId(),
            attendeeEntity.getName(),
            attendeeEntity.getAvatar(),
            attendeeEntity.isTuesdayOn(),
            attendeeEntity.isThursdayOn());
    }
}
