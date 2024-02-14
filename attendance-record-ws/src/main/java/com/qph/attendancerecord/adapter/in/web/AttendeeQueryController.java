package com.qph.attendancerecord.adapter.in.web;

import com.qph.attendancerecord.adapter.in.web.model.AttendeeResponse;
import com.qph.attendancerecord.application.port.in.AttendeeQueryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/attendees")
@Slf4j
@RequiredArgsConstructor
public class AttendeeQueryController {
    private final AttendeeQueryUseCase attendeeQueryUseCase;

    @GetMapping("/{id}")
    public AttendeeResponse getAttendeeById(@PathVariable String id) {
        log.info("GET ACTION # getting attendee by id # id={}",
            id);
        var attendee = this.attendeeQueryUseCase.getAttendeeById(id);
        return AttendeeResponse.fromAttendee(attendee);
    }
}
