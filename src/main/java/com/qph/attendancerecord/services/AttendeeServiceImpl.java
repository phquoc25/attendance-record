package com.qph.attendancerecord.services;

import com.qph.attendancerecord.entity.Attendee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeServiceImpl.class);
    private static final List<Attendee> ATTENDEES = Stream.of(
            new Attendee("Ho Quoc", true, true),
            new Attendee("Van Khanh", false, true),
            new Attendee("Nicolas", true, false),
            new Attendee("Dieu Vi", false, false),
            new Attendee("My Han", true, true),
            new Attendee("Han Nhon", true, true),
            new Attendee("Minh Quan", false, false),
            new Attendee("Pierre", true, false),
            new Attendee("Tuan Anh", false, false))
            .collect(Collectors.toList());
    @Override
    public List<Attendee> getAllAttendees() {
        LOGGER.debug("Getting all attendees");
        return ATTENDEES;
    }

    @Override
    public void updateAttendee(Attendee attendee) {
        LOGGER.debug("Updating attendees with id={}, name={}. New info presentOnTuesday={}, presentonThursday={}",
                attendee.getId(), attendee.getName(), attendee.isPresentOnTuesday(), attendee.isPresentOnThursday());
        ATTENDEES.stream().filter(att -> att.equals(attendee))
                .findFirst()
                .ifPresent(att -> {
                    att.setPresentOnTuesday(attendee.isPresentOnTuesday());
                    att.setPresentOnThursday(attendee.isPresentOnThursday());
                });
    }
}
