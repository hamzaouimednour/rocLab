package com.rocc.lrt.application.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Command object used to create a new Schedule.
 * This is what the frontend sends to the API.
 */
public record ScheduleCreateCommand(

        @NotBlank(message = "LRT number is required")
        @Size(min = 3, max = 20, message = "LRT number must be between 3 and 20 characters")
        String lrtNumber,

        @NotBlank(message = "Departure station is required")
        @Size(max = 100, message = "Departure station name is too long")
        String departureStation,

        @NotBlank(message = "Arrival station is required")
        @Size(max = 100, message = "Arrival station name is too long")
        String arrivalStation,

        @NotNull(message = "Departure time is required")
        @Future(message = "Departure time must be in the future")
        LocalDateTime departureTime,

        @NotNull(message = "Arrival time is required")
        LocalDateTime arrivalTime,

        @NotBlank(message = "Route code is required")
        @Size(min = 2, max = 10, message = "Route code must be between 2 and 10 characters")
        String routeCode,

        @Size(max = 500, message = "Notes are too long")
        String notes

) {
}
