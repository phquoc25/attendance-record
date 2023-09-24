package com.qph.attendancerecord.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendanceRecordControllerIT {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void getAttendees() {
        // GIVEN
        // WHEN
        List result = restTemplate.getForObject("http://localhost:" + port + "/attendees", List.class);
        List result2 = restTemplate.getForObject("http://localhost:" + port + "/attendees", List.class);

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result2).isNotEmpty();
    }
}