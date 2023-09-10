package com.qph.attendancerecord.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qph.attendancerecord.entity.Attendee;
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
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Component
@Slf4j
@RequiredArgsConstructor
public class AttendeeDaoImpl implements AttendeeDao {
    @Value("${file.name}")
    private String fileName;

    private final ObjectMapper objectMapper;
    

    @Override
    @Cacheable("attendees")
    public List<Attendee> getAllAttendees() {
        try (InputStream fileInputStream = new FileInputStream(fileName)) {
            return Arrays.stream(objectMapper.readValue(fileInputStream, Attendee[].class))
                    .collect(toList());
        } catch (IOException e) {
            log.error("Error when updating to DB", e);
            return new ArrayList<>();
        }
    }

    @Override
    synchronized public void updateAttendees(List<Attendee> attendees) throws IOException {
        log.debug("updating attendees to DB");
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            objectMapper.writeValue(fileOutputStream, attendees);
        } catch (IOException e) {
            log.error("Error when updating to DB", e);
            throw e;
        }
    }

    @Override
    synchronized public Attendee save(Attendee attendee) throws IOException {
        List<Attendee> allAttendees = this.getAllAttendees();
        List<Attendee> updatedAttendees;
        if (isNull(attendee.getId())) {
            attendee.setId(UUID.randomUUID().toString());
            log.info("Adding new attendee {}", attendee);
            updatedAttendees = new ArrayList<>(allAttendees);
        } else {
            log.info("Update attendee {}", attendee);
            updatedAttendees = allAttendees.stream().filter(attendee1 -> !attendee1.getId().equals(attendee.getId())).collect(toList());
        }
        updatedAttendees.add(attendee);
        this.updateAttendees(updatedAttendees);
        return attendee;
    }

    @Override
    public String remove(String id) throws IOException {
        List<Attendee> allAttendees = this.getAllAttendees();
        List<Attendee> updatedAttendees = allAttendees.stream().filter(attendee -> !attendee.getId().equals(id)).collect(toList());
        this.updateAttendees(updatedAttendees);
        return id;
    }


}
