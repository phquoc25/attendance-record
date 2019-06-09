package com.qph.attendancerecord.resources;

import com.qph.attendancerecord.entity.Attendee;
import com.qph.attendancerecord.services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attendees")
public class AttendanceRecordController {
    private AttendeeService attendeeService;

    @Autowired
    public AttendanceRecordController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping()
    public List<Attendee> getAttendees() {
        return attendeeService.getAllAttendees();
    }

    @PutMapping()
    public void updateAttendees(@RequestBody List<Attendee> attendees) throws IOException {
        attendeeService.updateAttendees(attendees);
    }

}
