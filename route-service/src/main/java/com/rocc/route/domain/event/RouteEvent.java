package com.rocc.route.domain.event;

import java.time.Instant;

public sealed interface RouteEvent permits LrtPositionUpdatedEvent {

    String lrtNumber();
    Instant timestamp();
}