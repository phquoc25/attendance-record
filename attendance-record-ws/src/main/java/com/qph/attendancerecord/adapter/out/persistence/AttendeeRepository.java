package com.qph.attendancerecord.adapter.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qph.attendancerecord.adapter.out.persistence.model.AttendeeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttendeeRepository {

    @Value("${file.name}")
    private String fileName;

    private final ObjectMapper objectMapper;


    @Cacheable("attendees")
    public List<AttendeeEntity> getAllAttendees() {
        log.info("Retrieving attendees from file # fileName={}",
            fileName);
        try (InputStream fileInputStream = new FileInputStream(fileName)) {
            return Arrays.stream(objectMapper.readValue(fileInputStream,
                    AttendeeEntity[].class))
                .collect(toList());
        } catch (IOException e) {
            log.error("Error when updating to DB",
                e);
            return new ArrayList<>();
        }
    }

    synchronized public void updateAttendees(List<AttendeeEntity> attendees) throws IOException {
        log.debug("updating attendees to DB");
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            objectMapper.writeValue(fileOutputStream,
                attendees);
        } catch (IOException e) {
            log.error("Error when updating to DB",
                e);
            throw e;
        }
    }

    synchronized public AttendeeEntity save(AttendeeEntity attendee) throws IOException {
        List<AttendeeEntity> allAttendees = this.getAllAttendees();
        List<AttendeeEntity> updatedAttendees;
        if (isNull(attendee.getId())) {
            attendee.setId(UUID.randomUUID().toString());
            log.info("Adding new attendee {}",
                attendee);
            updatedAttendees = new ArrayList<>(allAttendees);
        } else {
            log.info("Update attendee {}",
                attendee);
            updatedAttendees = allAttendees.stream().filter(attendee1 -> !attendee1.getId().equals(attendee.getId())).collect(toList());
        }
        updatedAttendees.add(attendee);
        this.updateAttendees(updatedAttendees);
        return attendee;
    }
}
