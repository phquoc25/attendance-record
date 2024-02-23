package com.qph.attendancerecord.common.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorImpl implements IdGenerator {
    @Override
    public String generateStringId() {
        return UUID.randomUUID().toString();
    }
}
