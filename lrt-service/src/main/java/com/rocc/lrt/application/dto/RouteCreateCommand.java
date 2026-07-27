package com.rocc.lrt.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Command object used to create a new Route.
 * This is what the frontend sends to the API.
 */
public record RouteCreateCommand(

        @NotBlank(message = "Route code is required")
        @Size(min = 2, max = 10, message = "Route code must be between 2 and 10 characters")
        String routeCode,

        @NotBlank(message = "From station is required")
        @Size(max = 100, message = "From station name is too long")
        String fromStation,

        @NotBlank(message = "To station is required")
        @Size(max = 100, message = "To station name is too long")
        String toStation,

        @Size(max = 500, message = "Description is too long")
        String description

) {
}
