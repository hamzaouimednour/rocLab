package com.rocc.lrt.domain.event;

import java.time.Instant;

public sealed interface RouteEvent permits
        RouteCreatedEvent,
        RouteUpdatedEvent,
        RouteDeletedEvent {

    String routeCode();
    Instant timestamp();
}
