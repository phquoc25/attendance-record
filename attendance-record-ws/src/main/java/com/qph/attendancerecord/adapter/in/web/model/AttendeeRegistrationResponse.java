package com.qph.attendancerecord.adapter.in.web.model;

public record AttendeeRegistrationResponse(String id, String name, String avatar, boolean tuesdayOn,
                                           boolean thursdayOn) {
}
