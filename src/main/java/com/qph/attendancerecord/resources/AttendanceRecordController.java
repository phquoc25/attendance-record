package com.qph.attendancerecord.resources;

import com.qph.attendancerecord.entity.Attendee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AttendanceRecordController {

    @GetMapping("/attendees")
    public List<Attendee> getAttendees() {
        return Stream.of(new Attendee("Quoc", true, true),
                        new Attendee("Khanh", false, true),
                        new Attendee("Nico", true, false))
                .collect(Collectors.toList());
    }

}
