package com.qph.attendancerecord.services;

import com.qph.attendancerecord.dao.AttendeeDao;
import com.qph.attendancerecord.entity.Attendee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeServiceImpl.class);

    private AttendeeDao attendeeDao;

    @Autowired
    public AttendeeServiceImpl(AttendeeDao attendeeDao) {
        this.attendeeDao = attendeeDao;
    }

    @Override
    public List<Attendee> getAllAttendees() {
        LOGGER.debug("Getting all attendees");
        return attendeeDao.getAllAttendees();
    }

    @Override
    public void updateAttendees(List<Attendee> attendees) throws IOException {
        LOGGER.debug("Updating attendees {}", attendees);
        attendeeDao.updateAttendees(attendees);
    }
}
