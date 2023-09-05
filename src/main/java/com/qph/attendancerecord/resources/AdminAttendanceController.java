package com.qph.attendancerecord.resources;

import com.qph.attendancerecord.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/admin/attendees")
@Slf4j
@RequiredArgsConstructor
public class AdminAttendanceController {
    private final AttendeeService attendeeService;
    @DeleteMapping("/{id}")
    public String deleteAttendee(@PathVariable String id) throws IOException {
        log.info("DELETE attendee # id={}", id);
        return attendeeService.remove(id);
    }
}
