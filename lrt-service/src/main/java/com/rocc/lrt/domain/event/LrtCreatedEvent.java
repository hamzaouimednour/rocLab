package com.rocc.lrt.domain.event;

import com.rocc.lrt.domain.model.LrtType;

import java.time.Instant;

public record LrtCreatedEvent(
        String trainNumber,
        LrtType type,
        String operator,
        Instant timestamp
) implements LrtEvent {
    public LrtCreatedEvent(String trainNumber, LrtType type, String operator) {
        this(trainNumber, type, operator, Instant.now());
    }
}
