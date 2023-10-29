package com.qph.attendancerecord.adapter.in.web;

import com.qph.attendancerecord.adapter.in.web.model.AttendeeRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AttendeeRegistrationController {
    @PostMapping("/attendees")
    public void registerAttendee(@RequestBody AttendeeRegistrationRequest request) {

    }
}
