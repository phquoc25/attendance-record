package com.qph.attendancerecord.adapter.in.web;

import com.qph.attendancerecord.adapter.in.web.model.AttendeeRegistrationRequest;
import com.qph.attendancerecord.adapter.in.web.model.AttendeeRegistrationResponse;
import com.qph.attendancerecord.application.domain.model.Attendee;
import com.qph.attendancerecord.application.port.in.RegisterAttendeeUseCase;
import com.qph.attendancerecord.application.port.in.model.Name;
import com.qph.attendancerecord.application.port.in.model.RegisterAttendeeCommand;
import com.qph.attendancerecord.application.port.in.model.ThursdayOn;
import com.qph.attendancerecord.application.port.in.model.TuesdayOn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
@Slf4j
@RequiredArgsConstructor
public class AttendeeRegistrationController {
    private final RegisterAttendeeUseCase registerAttendeeUseCase;

    @PostMapping("/attendees")
    @ResponseStatus(HttpStatus.CREATED)
    public AttendeeRegistrationResponse registerAttendee(@RequestBody AttendeeRegistrationRequest request) {
        log.info("ACTION LOG # Start registering a new attendee # request={}",
            request);
        Name name = Name.fromString(request.name());
        TuesdayOn tuesdayOn = TuesdayOn.fromString(request.tuesdayOn());
        ThursdayOn thursdayOn = ThursdayOn.fromString(request.thursdayOn());
        RegisterAttendeeCommand registerAttendeeCommand = new RegisterAttendeeCommand(name,
            tuesdayOn,
            thursdayOn);
        Attendee attendee = registerAttendeeUseCase.registerAttendee(registerAttendeeCommand);
        return new AttendeeRegistrationResponse(attendee.getId(),
            attendee.getName(),
            attendee.getAvatar(),
            attendee.isTuesdayOn(),
            attendee.isThursdayOn());
    }
}
