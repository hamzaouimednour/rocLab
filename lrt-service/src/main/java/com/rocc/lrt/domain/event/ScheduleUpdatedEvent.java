package com.rocc.lrt.domain.event;

import com.rocc.lrt.domain.model.ScheduleStatus;

import java.time.Instant;

public record ScheduleUpdatedEvent(
        String lrtNumber,
        ScheduleStatus status,
        Instant timestamp
) implements ScheduleEvent {
    public ScheduleUpdatedEvent(String lrtNumber, ScheduleStatus status) {
        this(lrtNumber, status, Instant.now());
    }
}
