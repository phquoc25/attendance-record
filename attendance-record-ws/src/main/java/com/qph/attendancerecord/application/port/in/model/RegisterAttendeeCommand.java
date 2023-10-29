package com.qph.attendancerecord.application.port.in.model;

/*
 * immutable. Validate in the constructor
 */
public record RegisterAttendeeCommand(Name name, TuesdayOn tuesdayOn, ThursdayOn thursdayOn) {
}
