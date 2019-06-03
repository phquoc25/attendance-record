package com.qph.attendancerecord.resources;

import com.qph.attendancerecord.entity.Attendee;
import com.qph.attendancerecord.services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendees")
public class AttendanceRecordController {
    @Autowired
    private AttendeeService attendeeService;

    @GetMapping()
    public List<Attendee> getAttendees() {
        return attendeeService.getAllAttendees();
    }

    @PutMapping()
    public void updateAttendee(@RequestBody Attendee attendee) {
        attendeeService.updateAttendee(attendee);
    }

}
