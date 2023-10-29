package com.qph.attendancerecord.common.service;

import java.util.UUID;

public class IdGeneratorImpl implements IdGenerator {
    @Override
    public String generateStringId() {
        return UUID.randomUUID().toString();
    }
}
