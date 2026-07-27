package com.rocc.interlocking.application.dto;

import jakarta.validation.constraints.NotBlank;

public record RouteRequestCommand(
        @NotBlank String lrtNumber,
        @NotBlank String fromStation,
        @NotBlank String toStation,
        String requestedRouteId
) {
}