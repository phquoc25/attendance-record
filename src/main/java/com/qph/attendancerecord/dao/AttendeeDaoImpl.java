package com.qph.attendancerecord.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qph.attendancerecord.entity.Attendee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttendeeDaoImpl implements AttendeeDao {
    private static Logger LOGGER = LoggerFactory.getLogger(AttendeeDaoImpl.class);

    @Value("${file.name}")
    private String fileName;

    private ObjectMapper objectMapper;

    @Autowired
    public AttendeeDaoImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Attendee> getAllAttendees() {
        try (InputStream fileInputStream = new FileInputStream(fileName)) {
            return Arrays.stream(objectMapper.readValue(fileInputStream, Attendee[].class))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error when updating to DB", e);
            return new ArrayList<>();
        }
    }

    @Override
    synchronized public void updateAttendees(List<Attendee> attendees) throws IOException {
        LOGGER.debug("updating attendees to DB");
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            objectMapper.writeValue(fileOutputStream, attendees);
        } catch (IOException e) {
            LOGGER.error("Error when updating to DB", e);
            throw e;
        }
    }
}
