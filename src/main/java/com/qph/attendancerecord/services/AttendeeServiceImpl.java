package com.qph.attendancerecord.services;

import com.qph.attendancerecord.dao.AttendeeDao;
import com.qph.attendancerecord.entity.Attendee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeDao attendeeDao;

    @Override
    public List<Attendee> getAllAttendees() {
        log.debug("Getting all attendees");
        return attendeeDao.getAllAttendees();
    }

    @Override
    public void updateAttendees(List<Attendee> attendees) throws IOException {
        log.debug("Updating attendees {}", attendees);
        attendeeDao.updateAttendees(attendees);
    }

    @Override
    public Attendee save(Attendee attendee) throws IOException {
        return attendeeDao.save(attendee);
    }

    @Override
    public String remove(String id) throws IOException {
        return attendeeDao.remove(id);
    }


}
