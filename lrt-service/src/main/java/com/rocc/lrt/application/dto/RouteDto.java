package com.rocc.lrt.application.dto;

import com.rocc.lrt.domain.model.Route;
import com.rocc.lrt.domain.model.RouteStatus;

import java.time.Instant;

public record RouteDto(
        Long id,
        String routeCode,
        String fromStation,
        String toStation,
        String description,
        RouteStatus status,
        Instant createdAt,
        Instant updatedAt
) {
    public static RouteDto from(Route route) {
        return new RouteDto(
                route.id(),
                route.routeCode(),
                route.fromStation(),
                route.toStation(),
                route.description(),
                route.status(),
                route.createdAt(),
                route.updatedAt()
        );
    }
}
