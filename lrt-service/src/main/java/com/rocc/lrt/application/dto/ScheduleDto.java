package com.rocc.lrt.application.dto;

import com.rocc.lrt.domain.model.Schedule;
import com.rocc.lrt.domain.model.ScheduleStatus;

import java.time.LocalDateTime;

public record ScheduleDto(
        Long id,
        String lrtNumber,
        String departureStation,
        String arrivalStation,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String routeCode,
        ScheduleStatus status,
        String notes
) {
    public static ScheduleDto from(Schedule schedule) {
        return new ScheduleDto(
                schedule.id(),
                schedule.lrtNumber(),
                schedule.departureStation(),
                schedule.arrivalStation(),
                schedule.departureTime(),
                schedule.arrivalTime(),
                schedule.routeCode(),
                schedule.status(),
                schedule.notes()
        );
    }
}
