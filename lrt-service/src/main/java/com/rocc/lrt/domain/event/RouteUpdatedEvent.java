package com.rocc.lrt.domain.event;

import com.rocc.lrt.domain.model.RouteStatus;

import java.time.Instant;

public record RouteUpdatedEvent(
        String routeCode,
        RouteStatus status,
        Instant timestamp
) implements RouteEvent {
    public RouteUpdatedEvent(String routeCode, RouteStatus status) {
        this(routeCode, status, Instant.now());
    }
}
