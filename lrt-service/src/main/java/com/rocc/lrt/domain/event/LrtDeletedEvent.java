package com.rocc.lrt.domain.event;

import com.rocc.lrt.domain.model.LrtType;

import java.time.Instant;

public record LrtDeletedEvent(
        String trainNumber,
        LrtType type,
        Instant timestamp
) implements LrtEvent {
    public LrtDeletedEvent(String trainNumber, LrtType type) {
        this(trainNumber, type, Instant.now());
    }
}