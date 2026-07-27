package com.rocc.lrt.domain.event;

import com.rocc.lrt.domain.model.ScheduleStatus;

import java.time.Instant;
import java.time.LocalDateTime;

public record ScheduleCreatedEvent(
        String lrtNumber,
        String routeCode,
        String departureStation,
        String arrivalStation,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        ScheduleStatus status,
        Instant timestamp
) implements ScheduleEvent {
    public ScheduleCreatedEvent(String lrtNumber, String routeCode, String departureStation,
                               String arrivalStation, LocalDateTime departureTime,
                               LocalDateTime arrivalTime, ScheduleStatus status) {
        this(lrtNumber, routeCode, departureStation, arrivalStation, departureTime, arrivalTime, status, Instant.now());
    }
}
