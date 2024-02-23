package com.qph.attendancerecord.adapter.in.web;

import com.qph.attendancerecord.adapter.in.web.exception.ApplicationServerError;
import com.qph.attendancerecord.adapter.in.web.model.AttendeeRegistrationRequest;
import com.qph.attendancerecord.adapter.in.web.model.AttendeeRegistrationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendeeRegistrationControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test_RegisterAttendee_given_valid_request_then_return_201_with_body() {
        // GIVEN
        AttendeeRegistrationRequest request = new AttendeeRegistrationRequest();
        String name = "test name";
        request.setName(name);
        request.setTuesdayOn("true");
        request.setThursdayOn("false");
        String url = "http://localhost:" + port + "/v2/attendees";
        // WHEN

        ResponseEntity<AttendeeRegistrationResponse> response = restTemplate.postForEntity(url, request, AttendeeRegistrationResponse.class);
        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().name()).isEqualTo(name);
        assertThat(response.getBody().tuesdayOn()).isTrue();
        assertThat(response.getBody().thursdayOn()).isFalse();
    }

    @Test
    void test_RegisterAttendee_given_invalid_request_then_return_400() {
        // GIVEN
        AttendeeRegistrationRequest request = new AttendeeRegistrationRequest();
        String name = "test name";
        request.setName(name);
        request.setTuesdayOn("12");
        request.setThursdayOn("false");
        String url = "http://localhost:" + port + "/v2/attendees";
        // WHEN

        ResponseEntity<ApplicationServerError> response = restTemplate.postForEntity(url, request, ApplicationServerError.class);
        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().code()).isEqualTo(ApplicationServerError.VALIDATION_FAILURE);
        assertThat(response.getBody().errorMessage()).isEqualTo("TuesdayOn must be true or false but get 12");
    }
}