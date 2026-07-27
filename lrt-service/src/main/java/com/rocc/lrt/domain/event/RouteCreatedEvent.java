package com.rocc.lrt.domain.event;

import com.rocc.lrt.domain.model.RouteStatus;

import java.time.Instant;

public record RouteCreatedEvent(
        String routeCode,
        String fromStation,
        String toStation,
        RouteStatus status,
        Instant timestamp
) implements RouteEvent {
    public RouteCreatedEvent(String routeCode, String fromStation, String toStation, RouteStatus status) {
        this(routeCode, fromStation, toStation, status, Instant.now());
    }
}
