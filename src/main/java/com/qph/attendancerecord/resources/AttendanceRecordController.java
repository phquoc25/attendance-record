package com.qph.attendancerecord.resources;

import com.qph.attendancerecord.entity.Attendee;
import com.qph.attendancerecord.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attendees")
@Slf4j
@RequiredArgsConstructor
public class AttendanceRecordController {
    private final AttendeeService attendeeService;

    @GetMapping()
    public List<Attendee> getAttendees() {
        return attendeeService.getAllAttendees();
    }

    @PutMapping()
    public void updateAttendees(@RequestBody List<Attendee> attendees) throws IOException {
        attendeeService.updateAttendees(attendees);
    }

    @PostMapping()
    public Attendee addAttendee(@RequestBody Attendee attendee) throws IOException {
        return attendeeService.save(attendee);
    }

}
