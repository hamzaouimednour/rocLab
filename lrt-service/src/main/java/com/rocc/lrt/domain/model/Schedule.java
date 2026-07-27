package com.rocc.lrt.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;


public record Schedule(
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

    public Schedule {
        Objects.requireNonNull(lrtNumber, "LRT number is required");
        Objects.requireNonNull(departureStation, "Departure station is required");
        Objects.requireNonNull(arrivalStation, "Arrival station is required");
        Objects.requireNonNull(departureTime, "Departure time is required");
        Objects.requireNonNull(arrivalTime, "Arrival time is required");
        Objects.requireNonNull(routeCode, "Route code is required");
        Objects.requireNonNull(status, "Schedule status is required");

        if (lrtNumber.isBlank()) {
            throw new IllegalArgumentException("LRT number cannot be blank");
        }
        if (departureStation.isBlank() || arrivalStation.isBlank()) {
            throw new IllegalArgumentException("Station names cannot be blank");
        }
        if (routeCode.isBlank()) {
            throw new IllegalArgumentException("Route code cannot be blank");
        }
        if (departureTime.isAfter(arrivalTime)) {
            throw new IllegalArgumentException("Departure time cannot be after arrival time");
        }
        if (departureTime.isBefore(LocalDateTime.now().minusHours(2))) {
            throw new IllegalArgumentException("Departure time cannot be too far in the past");
        }
    }


    public static Schedule createNew(
            String lrtNumber,
            String departureStation,
            String arrivalStation,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime,
            String routeCode) {

        return new Schedule(
                null,
                lrtNumber.trim(),
                departureStation.trim(),
                arrivalStation.trim(),
                departureTime,
                arrivalTime,
                routeCode.trim(),
                ScheduleStatus.PLANNED,
                null
        );
    }
}