package com.qph.attendancerecord.adapter.in.web.model;

import lombok.Data;

@Data
public class AttendeeRegistrationRequest {
    private String name;
    private Boolean tuesDayOn;
    private Boolean thursdayOn;
}
