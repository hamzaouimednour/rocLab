package com.rocc.lrt.domain.model;

import java.time.Instant;
import java.util.Objects;


public record Route(
        Long id,
        String routeCode,
        String fromStation,
        String toStation,
        String description,
        RouteStatus status,
        Instant createdAt,
        Instant updatedAt
) {

    public Route {
        Objects.requireNonNull(routeCode, "Route code is required");
        Objects.requireNonNull(fromStation, "From station is required");
        Objects.requireNonNull(toStation, "To station is required");
        Objects.requireNonNull(status, "Status is required");

        if (routeCode.isBlank()) {
            throw new IllegalArgumentException("Route code cannot be blank");
        }
    }

    public static Route createNew(String routeCode, String fromStation, String toStation, String description) {
        return new Route(null, routeCode, fromStation, toStation, description,
                RouteStatus.AVAILABLE, Instant.now(), Instant.now());
    }
}