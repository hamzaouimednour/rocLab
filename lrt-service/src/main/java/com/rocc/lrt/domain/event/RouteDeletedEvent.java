package com.rocc.lrt.domain.event;

import java.time.Instant;

public record RouteDeletedEvent(
        String routeCode,
        Instant timestamp
) implements RouteEvent {
    public RouteDeletedEvent(String routeCode) {
        this(routeCode, Instant.now());
    }
}
