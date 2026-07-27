package com.rocc.route.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LrtPositionCommand(

        @NotBlank(message = "LRT number is required")
        String lrtNumber,

        @Min(value = -90, message = "Latitude must be between -90 and 90")
        @Max(value = 90, message = "Latitude must be between -90 and 90")
        double latitude,

        @Min(value = -180, message = "Longitude must be between -180 and 180")
        @Max(value = 180, message = "Longitude must be between -180 and 180")
        double longitude,

        @Min(value = 0, message = "Speed cannot be negative")
        int speedKmh
) {
}