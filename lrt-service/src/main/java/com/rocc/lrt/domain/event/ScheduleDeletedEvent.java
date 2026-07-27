package com.rocc.lrt.domain.event;

import java.time.Instant;

public record ScheduleDeletedEvent(
        String lrtNumber,
        Instant timestamp
) implements ScheduleEvent {
    public ScheduleDeletedEvent(String lrtNumber) {
        this(lrtNumber, Instant.now());
    }
}
