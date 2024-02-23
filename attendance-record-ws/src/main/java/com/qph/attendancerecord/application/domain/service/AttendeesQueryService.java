package com.qph.attendancerecord.application.domain.service;

import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.in.AttendeeQueryUseCase;
import com.qph.attendancerecord.application.port.out.AttendeeQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeesQueryService implements AttendeeQueryUseCase {
    private final AttendeeQueryPort attendeeQueryPort;

    @Override
    public Attendee getAttendeeById(String id) {
        return this.attendeeQueryPort.getById(id);
    }

    @Override
    public List<Attendee> getAllAttendees() {
        return this.attendeeQueryPort.getAll();
    }
}
